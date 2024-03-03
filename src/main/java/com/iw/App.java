package com.iw;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.http.staticfiles.Location;

public class App {
    public static void main(String[] args) {
        Javalin.create(cfg -> cfg.staticFiles.add("/assets/public", Location.CLASSPATH))
                .get("/", ctx -> ctx.result("Home page"))
                .error(HttpStatus.NOT_FOUND, ctx -> ctx.result("404. Page not found"))
                .start(8080);
    }
}
