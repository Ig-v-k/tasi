package com.iw.comment;

import com.iw.Comment;
import com.iw.Container;
import com.iw.User;
import com.iw.user.SqlUser;

import java.sql.*;
import java.util.Arrays;

public final class SqlComment implements Comment {

    private final Container container;
    private final int id;

    public SqlComment(Container container, int id) {
        this.container = container;
        this.id = id;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public int issue() {
        final String query = String.format("SELECT issue FROM comment WHERE id = %s", id);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final int issue = rs.getInt("issue");
                return issue;
            } else {
                final String mes = String.format(
                        "Cannot find column 'issue' with query: \"%s\", and arguments: %s",
                        query, Arrays.toString(new int[]{id}));
                throw new RuntimeException(mes);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User reporter() {
        final String query = String.format("SELECT reporter FROM comment WHERE id = %s", id);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final int reporter = rs.getInt("reporter");
                return new SqlUser(container, reporter);
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

    @Override
    public String summary() {
        final String query = String.format("SELECT summary FROM comment WHERE id = %s", id);
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
    public String text() {
        final String query = String.format("SELECT text FROM comment WHERE id = %s", id);
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

    @Override
    public boolean update(int issue, String summary, String text) {
        final String sql = "UPDATE comment SET issue = ?, summary = ?, text = ? WHERE id = ?";
        try (final Connection conn = container.conn();
             final PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, issue);
            st.setString(2, summary);
            st.setString(3, text);
            st.setInt(4, id);
            int affected = st.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete() {
        final String sql = "DELETE FROM comment WHERE id = ?";
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
