package com.iw.logs;

import com.iw.Container;
import com.iw.Issue;
import com.iw.Log;
import com.iw.Logs;
import com.iw.issue.SqlIssue;
import com.iw.log.SqlLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class SqlLogs implements Logs {

    private final Container container;

    public SqlLogs(Container container) {
        this.container = container;
    }

    @Override
    public List<Log> list() {
        final List<Log> logs = new ArrayList<>();
        final String query = "SELECT * FROM log";
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                final int id = rs.getInt("id");
                logs.add(new SqlLog(container, id));
            }
            return logs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(String text) {
        final String sql = "INSERT INTO log (text) VALUES (?)";
        try (final Connection conn = container.conn();
             final PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, text);
            int affected = st.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
