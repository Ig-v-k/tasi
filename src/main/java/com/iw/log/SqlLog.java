package com.iw.log;

import com.iw.Container;
import com.iw.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public final class SqlLog implements Log {

    private final Container container;
    private final int id;

    public SqlLog(Container container, int id) {
        this.container = container;
        this.id = id;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public String text() {
        final String query = String.format("SELECT text FROM log WHERE id = %s", id);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final String text = rs.getString("text");
                return text;
            } else {
                final String mes = String.format(
                        "Cannot find column 'text' with query: \"%s\", and arguments: %s",
                        query, Arrays.toString(new int[]{id}));
                throw new RuntimeException(mes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
