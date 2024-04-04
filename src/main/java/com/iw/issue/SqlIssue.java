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
    public int assignee() {
        final String query = String.format("SELECT assignee FROM issue WHERE id = %s", id);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final int assignee = rs.getInt("assignee");
                return assignee;
            } else {
                final String mes = String.format(
                        "Cannot find column 'assignee' with query: \"%s\", and arguments: %s",
                        query, Arrays.toString(new int[]{id}));
                throw new RuntimeException(mes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int reporter() {
        final String query = String.format("SELECT reporter FROM issue WHERE id = %s", id);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final int reporter = rs.getInt("reporter");
                return reporter;
            } else {
                final String mes = String.format(
                        "Cannot find column 'reporter' with query: \"%s\", and arguments: %s",
                        query, Arrays.toString(new int[]{id}));
                throw new RuntimeException(mes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int priority() {
        final String query = String.format("SELECT priority FROM issue WHERE id = %s", id);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final int priority = rs.getInt("priority");
                return priority;
            } else {
                final String mes = String.format(
                        "Cannot find column 'priority' with query: \"%s\", and arguments: %s",
                        query, Arrays.toString(new int[]{id}));
                throw new RuntimeException(mes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int status() {
        final String query = String.format("SELECT status FROM issue WHERE id = %s", id);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final int status = rs.getInt("status");
                return status;
            } else {
                final String mes = String.format(
                        "Cannot find column 'status' with query: \"%s\", and arguments: %s",
                        query, Arrays.toString(new int[]{id}));
                throw new RuntimeException(mes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long created() {
        final String query = String.format("SELECT created FROM issue WHERE id = %s", id);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final long created = rs.getLong("created");
                return created;
            } else {
                final String mes = String.format(
                        "Cannot find column 'created' with query: \"%s\", and arguments: %s",
                        query, Arrays.toString(new int[]{id}));
                throw new RuntimeException(mes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long updated() {
        final String query = String.format("SELECT updated FROM issue WHERE id = %s", id);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final long updated = rs.getLong("updated");
                return updated;
            } else {
                final String mes = String.format(
                        "Cannot find column 'updated' with query: \"%s\", and arguments: %s",
                        query, Arrays.toString(new int[]{id}));
                throw new RuntimeException(mes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long resolved() {
        final String query = String.format("SELECT resolved FROM issue WHERE id = %s", id);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final long resolved = rs.getLong("resolved");
                return resolved;
            } else {
                final String mes = String.format(
                        "Cannot find column 'resolved' with query: \"%s\", and arguments: %s",
                        query, Arrays.toString(new int[]{id}));
                throw new RuntimeException(mes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(String summary,
                          String description,
                          int assignee,
                          int reporter,
                          int priority,
                          int status,
                          long created,
                          long updated,
                          long resolved) {
        final String sql = "UPDATE issue SET summary = ?, description = ?, assignee = ?, reporter = ?, priority = ?, " +
                "status = ?, created = ?, updated = ?, resolved = ? WHERE id = ?";
        try (final Connection conn = container.conn();
             final PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, summary);
            st.setString(2, description);
            st.setInt(3, assignee);
            st.setInt(4, reporter);
            st.setInt(5, priority);
            st.setInt(6, status);
            st.setLong(7, created);
            st.setLong(8, updated);
            st.setLong(9, resolved);
            st.setInt(10, id);
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
