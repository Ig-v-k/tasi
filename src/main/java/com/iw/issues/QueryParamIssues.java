package com.iw.issues;

import com.iw.Container;
import com.iw.Issue;
import com.iw.Issues;
import com.iw.issue.SqlIssue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class QueryParamIssues implements Issues {

    final Container container;

    final Map<String, List<String>> params;

    public QueryParamIssues(Container container, Map<String, List<String>> params) {
        this.container = container;
        this.params = params;
    }

    @Override
    public List<Issue> all() {
        final List<Issue> issues = new ArrayList<>();
        final String where = where();
        final String query = String.format("SELECT * FROM issue%s", where);
        try (final Connection conn = container.conn()) {
            final PreparedStatement pst = conn.prepareStatement(query);
            int index = 1;
            if (params.containsKey("search")) {
                pst.setString(index++, "%" + params.get("search") + "%");
            }
            if (params.containsKey("type")) {
                pst.setString(index++, params.get("type").toString());
            }
            if (params.containsKey("status")) {
                pst.setString(index++, params.get("status").toString());
            }
            if (params.containsKey("assignee")) {
                pst.setString(index++, params.get("status").toString());
            }
            try (final ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    final int id = rs.getInt("id");
                    issues.add(new SqlIssue(this.container, id));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return issues;
    }

    private String where() {
        return new ArrayList<String>() {{
            if (params.containsKey("search")) {
                add("summary LIKE ?");
            }
            if (params.containsKey("type")) {
                add("status = ?");
            }
            if (params.containsKey("status")) {
                add("status = ?");
            }
            if (params.containsKey("assignee")) {
                add("assignee = ?");
            }
        }}.stream().collect(Collectors.joining(" AND ", " WHERE ", ""));
    }

    @Override
    public Issue bySummary(String title) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean add(String title) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
