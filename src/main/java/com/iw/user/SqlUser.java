package com.iw.user;

import com.iw.Container;
import com.iw.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public final class SqlUser implements User {

    private final Container container;
    private final int id;

    public SqlUser(Container container, int id) {
        this.container = container;
        this.id = id;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public String name() {
        final String query = String.format("SELECT name FROM \"user\" WHERE id = %s", id);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final String name = rs.getString("name");
                return name;
            } else {
                final String mes = String.format(
                        "Cannot find column 'name' with query: \"%s\", and arguments: %s",
                        query, Arrays.toString(new int[]{id}));
                throw new RuntimeException(mes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long submit() {
        final String query = String.format("SELECT submit FROM comment WHERE id = %s", id);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final long submit = rs.getLong("submit");
                return submit;
            } else {
                final String mes = String.format(
                        "Cannot find column 'submit' with query: \"%s\", and arguments: %s",
                        query, Arrays.toString(new int[]{id}));
                throw new RuntimeException(mes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
