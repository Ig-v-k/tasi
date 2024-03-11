package com.iw;

import com.iw.container.HikariContainer;
import com.iw.events.SqlEvents;
import com.iw.jdbc.PsqlJDBC;
import com.iw.page.EventPage;
import com.iw.page.HomePage;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.http.staticfiles.Location;

public class App {
    public static void main(String[] args) {
        final String username = System.getenv("username");
        final String password = System.getenv("password");
        final Container container = new HikariContainer(new PsqlJDBC(username, password));
        final Events events = new SqlEvents(container);

        final Page pHome = new HomePage(events);

        Javalin.create(cfg -> cfg.staticFiles.add("/assets/public", Location.CLASSPATH))
                .get("/", ctx -> ctx.html(pHome.render()))
                .get("/{id}", ctx -> ctx.html(new EventPage(container, ctx.pathParam("id")).render()))
                .post("/events/create", ctx -> {
                    final String title = ctx.formParam("title");
                    if (events.add(title)) {
                        ctx.redirect(title);
                    } else {
                        ctx.result("Create fail. Reload page.");
                    }
                })
                .error(HttpStatus.NOT_FOUND, ctx -> ctx.result("404. Page not found"))
                .start(8080);
    }
}
