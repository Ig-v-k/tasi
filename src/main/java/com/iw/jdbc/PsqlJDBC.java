package com.iw.jdbc;

import com.iw.JDBC;

public final class PsqlJDBC implements JDBC {

    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;

    public PsqlJDBC(String username, String password) {
        this("localhost", 5432, "tasi", username, password);
    }

    public PsqlJDBC(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    @Override
    public String url() {
        return String.format(
                "jdbc:postgresql://%s:%s/%s?user=%s&password=%s", host, port, database, username, password);
    }
}
