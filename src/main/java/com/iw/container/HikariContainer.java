package com.iw.container;

import com.iw.Container;
import com.iw.JDBC;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class HikariContainer implements Container {

    private final HikariDataSource ds;

    public HikariContainer(JDBC jdbc) {
        this(jdbc.url());
    }

    public HikariContainer(String url) {
        this.ds = new HikariDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setJdbcUrl(url);
        ds.setPoolName("hikariPool1");
        ds.setMaximumPoolSize(20);
        ds.setMinimumIdle(5);
        ds.setConnectionTimeout(20000);
    }

    @Override
    public Connection conn() throws SQLException {
        return ds.getConnection();
    }
}
