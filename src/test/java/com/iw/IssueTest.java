package com.iw;

import com.iw.container.SqlContainer;
import com.iw.issue.SqlIssue;
import com.iw.jdbc.PsqlJDBC;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class IssueTest extends AbsContainerTest {
    @Test
    public void id() {
        final String username = System.getenv("username");
        final String password = System.getenv("password");
        final Container container = new SqlContainer(new PsqlJDBC(username, password));
        final Issue issue = new SqlIssue(container, 1);
        final int id = issue.id();
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void title() {
        final Container container = new SqlContainer(new PsqlJDBC(username, password));
        final Issue issue = new SqlIssue(container, 1);
        final String title = issue.title();
        assertThat(title).isNotBlank();
    }
}
