package com.iw.facet;

import com.iw.Facet;
import j2html.tags.Tag;
import j2html.tags.specialized.DivTag;

import static j2html.TagCreator.div;
import static j2html.TagCreator.h3;

public final class TitleFacet implements Facet<DivTag> {

    private final String title;
    private final Facet<? extends Tag<?>> facet;

    public TitleFacet(String title, Facet<? extends Tag<?>> facet) {
        this.title = title;
        this.facet = facet;
    }

    @Override
    public Tag<DivTag> tag() {
        return div(h3(title), facet.tag());
    }
}
