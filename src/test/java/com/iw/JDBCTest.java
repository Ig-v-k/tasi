package com.iw;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class JDBCTest {
    @Test
    public void lambda() {
        final JDBC jdbc = () -> "foo";
        assertThat(jdbc.url()).isEqualTo("foo");
    }
}
