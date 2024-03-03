package com.iw.container;

import com.iw.Container;
import com.iw.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SqlContainer implements Container {

    private final String url;

    public SqlContainer(final JDBC jdbc) {
        this(jdbc.url());
    }

    public SqlContainer(String url) {
        this.url = url;
    }

    @Override
    public Connection con() throws SQLException {
        final Connection connection = DriverManager.getConnection(url);
        connection.setSchema("public");
        connection.setAutoCommit(false);
        return connection;
    }
}
