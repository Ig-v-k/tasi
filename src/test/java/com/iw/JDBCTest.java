package com.iw;

import com.iw.jdbc.PsqlJDBC;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public final class JDBCTest {
    @Test
    public void lambda() {
        final JDBC jdbc = () -> "foo";
        assertThat(jdbc.url()).isEqualTo("foo");
    }

    @Test
    public void urlDefault() {
        final JDBC jdbc = new PsqlJDBC("foo", "foo");
        final String expected = String.format(
                "jdbc:postgresql://localhost:5432/postgres?user=%s&password=%s", "foo", "foo");
        assertThat(jdbc.url()).isEqualTo(expected);
    }

    @Test
    public void urlByEnvCredentials() {
        final Map<String, String> envs = System.getenv();
        final String username = envs.get("username");
        final String password = envs.get("password");
        final JDBC jdbc = new PsqlJDBC(username, password);
        final String expected = String.format(
                "jdbc:postgresql://localhost:5432/postgres?user=%s&password=%s", username, password);
        assertThat(jdbc.url()).isEqualTo(expected);
    }
}
