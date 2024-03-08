package com.iw.facet;

import com.iw.Facet;
import j2html.tags.Tag;
import j2html.tags.specialized.SpanTag;

import static j2html.TagCreator.span;

public class TestFacet implements Facet<SpanTag> {
    @Override
    public Tag<SpanTag> tag() {
        return span("test span");
    }
}
