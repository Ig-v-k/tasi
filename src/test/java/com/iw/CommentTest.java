package com.iw;

import com.iw.comment.SqlComment;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class CommentTest extends AbsContainerTest {

    private Comment comment;

    @Before
    public void start() {
        comment = new SqlComment(container, 1);
    }

    @Test
    public void id() {
        final int id = comment.id();
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void text() {
        final String text = comment.text();
        assertThat(text).isNotBlank();
    }

    @Test
    public void event() {
        final int event = comment.event();
        assertThat(event).isEqualTo(1);
    }
}
