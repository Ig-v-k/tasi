package com.iw.comments;

import com.iw.Comment;
import com.iw.Comments;
import com.iw.Container;
import com.iw.comment.SqlComment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class SqlComments implements Comments {

    private final Container container;

    public SqlComments(Container container) {
        this.container = container;
    }

    @Override
    public List<Comment> all() {
        final List<Comment> comments = new ArrayList<>();
        final String query = "SELECT * FROM comment";
        try (final Connection conn = container.conn();
             final Statement st = conn.createStatement();
             final ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                final int id = rs.getInt("id");
                comments.add(new SqlComment(container, id));
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(final String summary, String text, int event) {
        final String sql = "INSERT INTO comment (summary, text, event) VALUES (?, ?, ?)";
        try (final Connection conn = container.conn();
             final PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, summary);
            st.setString(2, text);
            st.setInt(3, event);
            int affected = st.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
