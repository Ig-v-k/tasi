package com.iw.comments;

import com.iw.Comment;
import com.iw.Comments;
import com.iw.Container;
import com.iw.comment.SqlComment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class RefComments implements Comments {

    private final Container container;
    private final Comments comments;
    private final int issue;

    public RefComments(Container container, int issue) {
        this.container = container;
        this.comments = new SqlComments(container);
        this.issue = issue;
    }

    @Override
    public List<Comment> all() {
        final List<Comment> comments = new ArrayList<>();
        final String query = String.format("SELECT * FROM comment WHERE issue = %s", issue);
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
    public boolean add(String summary, String text, int issue, int reporter, long submit) {
        return comments.add(summary, text, issue, reporter, submit);
    }
}
