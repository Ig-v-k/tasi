package com.iw.facet;

import com.iw.Facet;
import j2html.tags.EmptyTag;

import static j2html.TagCreator.emptyTag;

public final class EmptyFacet implements Facet {
    @Override
    public EmptyTag tag() {
        return emptyTag("div");
    }
}
