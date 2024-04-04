package com.iw.issues;

import com.iw.Container;
import com.iw.Issue;
import com.iw.Issues;
import com.iw.issue.SqlIssue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class SqlIssues implements Issues {

    private final Container container;

    public SqlIssues(Container container) {
        this.container = container;
    }

    @Override
    public List<Issue> all() {
        final List<Issue> issues = new ArrayList<>();
        final String query = "SELECT * FROM issue";
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                final int id = rs.getInt("id");
                issues.add(new SqlIssue(container, id));
            }
            return issues;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Issue bySummary(final String title) {
        final String query = String.format("SELECT * FROM issue WHERE title = '%s'", title);
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                final int id = rs.getInt("id");
                return new SqlIssue(container, id);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(String title) {
        final String sql = "INSERT INTO issue (title) VALUES (?)";
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
