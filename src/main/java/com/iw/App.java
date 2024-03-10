package com.iw;

import com.iw.container.HikariContainer;
import com.iw.jdbc.PsqlJDBC;
import com.iw.page.HomePage;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.http.staticfiles.Location;

public class App {
    public static void main(String[] args) {
        final String username = System.getenv("username");
        final String password = System.getenv("password");
        final Container container = new HikariContainer(new PsqlJDBC(username, password));
        final Page pHome = new HomePage(container);
        Javalin.create(cfg -> cfg.staticFiles.add("/assets/public", Location.CLASSPATH))
                .get("/", ctx -> ctx.html(pHome.render()))
                .post("/events", ctx -> ctx.status(201))
                .error(HttpStatus.NOT_FOUND, ctx -> ctx.result("404. Page not found"))
                .start(8080);
    }
}
