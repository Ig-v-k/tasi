package com.iw.events;

import com.iw.Container;
import com.iw.Event;
import com.iw.Events;
import com.iw.event.SqlEvent;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public Event byTitle(final String title) {
        final String query = String.format("SELECT * FROM event WHERE title = '%s'", title);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final int id = rs.getInt("id");
                return new SqlEvent(container, id);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(String title) {
        final String sql = "INSERT INTO event (title) VALUES (?)";
        try (final Connection conn = container.conn();
             final PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, title);
            int affected = st.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
