package com.iw.issue;

import com.iw.Container;
import com.iw.Issue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public final class SqlIssue implements Issue {

    private final Container container;
    private final int id;

    public SqlIssue(Container container, int id) {
        this.container = container;
        this.id = id;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public String title() {
        final String query = String.format("SELECT title FROM issue WHERE id = %s", id);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final String title = rs.getString("title");
                return title;
            } else {
                final String mes = String.format(
                        "Cannot find column 'title' with query: \"%s\", and arguments: %s",
                        query, Arrays.toString(new int[]{id}));
                throw new RuntimeException(mes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
