package com.iw;

import com.iw.comment.SqlComment;
import com.iw.comments.SqlComments;
import com.iw.container.HikariContainer;
import com.iw.issues.SqlIssues;
import com.iw.jdbc.PsqlJDBC;
import com.iw.logs.SqlLogs;
import com.iw.page.HomePage;
import com.iw.page.IssuePage;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.http.staticfiles.Location;

import static io.javalin.apibuilder.ApiBuilder.*;

public class App {
    public static void main(String[] args) {
        final String username = System.getenv("username");
        final String password = System.getenv("password");
        final Container container = new HikariContainer(new PsqlJDBC(username, password));
        final Issues issues = new SqlIssues(container);
        final Logs logs = new SqlLogs(container);

        final Page pHome = new HomePage(issues);

        Javalin.create(cfg -> {
                    cfg.staticFiles.add("/assets/public", Location.CLASSPATH);
                    cfg.router.apiBuilder(() -> path("/comment", () -> {
                        post("/add", ctx -> {
                            final String summary = ctx.formParam("summary");
                            final String text = ctx.formParam("text");
                            final Integer issue = ctx.formParamAsClass("issue", Integer.class).get();
                            if (new SqlComments(container).add(summary, text, issue)) {
                                logs.add("Comment added: " + summary);
                                ctx.redirect("/issue/" + issue);
                            } else {
                                ctx.result("Create fail. Reload page.");
                            }
                        });
                        post("/update", ctx -> {
                            final String summary = ctx.formParam("summary");
                            final String text = ctx.formParam("text");
                            final Integer issue = ctx.formParamAsClass("issue", Integer.class).get();
                            final Integer comment = ctx.formParamAsClass("comment", Integer.class).get();
                            if (new SqlComment(container, comment).update(issue, summary, text)) {
                                logs.add("Comment updated to: " + summary);
                                ctx.redirect("/issue/" + issue);
                            } else {
                                ctx.result("Update fail. Reload page.");
                            }
                        });
                    }));
                })
                .get("/", ctx -> ctx.html(pHome.render()))
                .get("/issue/{id}", ctx -> ctx.html(new IssuePage(container, ctx.pathParam("id")).render()))
                .post("/issues/create", ctx -> {
                    final String title = ctx.formParam("title");
                    if (issues.add(title)) {
                        final Issue issue = issues.byTitle(title);
                        logs.add("Issue created: " + title);
                        ctx.redirect("/issue/" + issue.id());
                    } else {
                        ctx.result("Create fail. Reload page.");
                    }
                })
                .error(HttpStatus.NOT_FOUND, ctx -> ctx.result("404. Page not found"))
                .start(8080);
    }
}
