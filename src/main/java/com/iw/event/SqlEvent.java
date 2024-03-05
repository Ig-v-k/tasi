package com.iw.event;

import com.iw.Container;
import com.iw.Event;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public final class SqlEvent implements Event {

    private final Container container;
    private final int id;

    public SqlEvent(Container container, int id) {
        this.container = container;
        this.id = id;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public String title() {
        final String query = String.format("SELECT title FROM event WHERE id = %s", id);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            final int row = rs.getRow();
            if (row > 0) {
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
