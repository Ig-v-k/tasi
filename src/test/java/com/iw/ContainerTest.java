package com.iw;

import com.iw.container.SqlContainer;
import com.iw.jdbc.PsqlJDBC;
import org.junit.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

public final class ContainerTest {
    /* Windows - Turn on PostgreSQL service*/
    @Test
    public void connectionSuccess() {
        final String username = System.getenv("username");
        final String password = System.getenv("password");
        final Container container = new SqlContainer(new PsqlJDBC(username, password));
        try(final Connection connection = container.con()) {
            assertThat(connection).isNotNull();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
