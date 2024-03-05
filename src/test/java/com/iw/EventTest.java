package com.iw;

import com.iw.container.SqlContainer;
import com.iw.event.SqlEvent;
import com.iw.jdbc.PsqlJDBC;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class EventTest {
    @Test
    public void id() {
        final String username = System.getenv("username");
        final String password = System.getenv("password");
        final Container container = new SqlContainer(new PsqlJDBC(username, password));
        final Event event = new SqlEvent(container, 1);
        final int id = event.id();
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void title() {
        final String username = System.getenv("username");
        final String password = System.getenv("password");
        final Container container = new SqlContainer(new PsqlJDBC(username, password));
        final Event event = new SqlEvent(container, 1);
        final String title = event.title();
        assertThat(title).isNotBlank();
    }
}
