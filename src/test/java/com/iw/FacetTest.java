package com.iw;

import com.iw.facet.TestFacet;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class FacetTest {
    @Test
    public void render() {
        final Facet fTest = new TestFacet();
        assertThat(fTest.render()).isEqualTo("<span>test span</span>");
    }
}
