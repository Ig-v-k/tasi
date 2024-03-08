package com.iw;

import com.iw.facet.TestFacet;
import j2html.tags.specialized.SpanTag;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class FacetTest {
    @Test
    public void render() {
        final Facet<SpanTag> fTest = new TestFacet();
        assertThat(fTest.tag().render()).isEqualTo("<span>test span</span>");
    }
}
