package com.iw.issue;

import com.iw.Container;
import com.iw.Issue;

import java.sql.*;
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
    public String summary() {
        final String query = String.format("SELECT summary FROM issue WHERE id = %s", id);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final String summary = rs.getString("summary");
                return summary;
            } else {
                final String mes = String.format(
                        "Cannot find column 'summary' with query: \"%s\", and arguments: %s",
                        query, Arrays.toString(new int[]{id}));
                throw new RuntimeException(mes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String description() {
        final String query = String.format("SELECT description FROM issue WHERE id = %s", id);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final String description = rs.getString("description");
                return description;
            } else {
                final String mes = String.format(
                        "Cannot find column 'description' with query: \"%s\", and arguments: %s",
                        query, Arrays.toString(new int[]{id}));
                throw new RuntimeException(mes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(String title, String description) {
        final String sql = "UPDATE issue SET title = ?, description = ? WHERE id = ?";
        try (final Connection conn = container.conn();
             final PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, title);
            st.setString(2, description);
            st.setInt(3, id);
            int affected = st.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete() {
        final String sql = "DELETE FROM issue WHERE id = ?";
        try (final Connection conn = container.conn();
             final PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            int affected = st.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
