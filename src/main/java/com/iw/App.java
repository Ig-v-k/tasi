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

import java.util.Calendar;
import java.util.Date;

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
                    cfg.router.apiBuilder(() -> {
                        path("/comment", () -> {
                            post("/add", ctx -> {
                                final String summary = ctx.formParam("summary");
                                final String text = ctx.formParam("text");
                                final Integer issue = ctx.formParamAsClass("issue", Integer.class).get();
                                final int reporter = 1; // TODO: User.class entity table
                                final long submit = Calendar.getInstance().getTime().getTime();
                                if (new SqlComments(container).add(summary, text, issue, reporter, submit)) {
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
                            post("/delete", ctx -> {
                                final String summary = ctx.formParam("summary");
                                final Integer issue = ctx.formParamAsClass("issue", Integer.class).get();
                                final Integer comment = ctx.formParamAsClass("comment", Integer.class).get();
                                if (new SqlComment(container, comment).delete()) {
                                    logs.add("Comment deleted: " + summary);
                                    ctx.redirect("/issue/" + issue);
                                } else {
                                    ctx.result("Delete fail. Reload page.");
                                }
                            });
                        });
                        path("/issue", () -> {
                            post("/create", ctx -> {
                                final String title = ctx.formParam("title");
                                if (issues.add(title)) {
                                    final Issue issue = issues.byTitle(title);
                                    logs.add("Issue created: " + title);
                                    ctx.redirect("/issue/" + issue.id());
                                } else {
                                    ctx.result("Create fail. Reload page.");
                                }
                            });
                        });
                    });
                })
                .get("/", ctx -> ctx.html(pHome.render()))
                .get("/issue/{id}", ctx -> ctx.html(new IssuePage(container, ctx.pathParam("id")).render()))
                .error(HttpStatus.NOT_FOUND, ctx -> ctx.result("404. Page not found"))
                .start(8080);
    }
}
