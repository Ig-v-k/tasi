package com.iw.events;

import com.iw.Container;
import com.iw.Event;
import com.iw.Events;
import com.iw.event.SqlEvent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class SqlEvents implements Events {

    private final Container container;

    public SqlEvents(Container container) {
        this.container = container;
    }

    @Override
    public List<Event> all() {
        final List<Event> events = new ArrayList<>();
        final String query = "SELECT * FROM event";
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                final int id = rs.getInt("id");
                events.add(new SqlEvent(container, id));
            }
            return events;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
