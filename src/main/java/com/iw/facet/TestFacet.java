package com.iw.facet;

import com.iw.Facet;

import static j2html.TagCreator.span;

public class TestFacet implements Facet {
    @Override
    public String render() {
        return span("test span").render();
    }
}
