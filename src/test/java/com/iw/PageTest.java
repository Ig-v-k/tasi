package com.iw;

import com.iw.page.TestPage;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class PageTest {
    @Test
    public void render() {
        final Page pTest = new TestPage();
        assertThat(pTest.render()).isEqualTo("test");
    }
}
