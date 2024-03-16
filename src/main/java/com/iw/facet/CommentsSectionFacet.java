package com.iw.facet;

import com.iw.Facet;
import j2html.tags.Tag;
import j2html.tags.specialized.DivTag;

import static j2html.TagCreator.div;
import static j2html.TagCreator.h4;

public class CommentsSectionFacet implements Facet<DivTag> {

    private final Facet<? extends Tag<?>> facet;

    public CommentsSectionFacet(Facet<? extends Tag<?>> facet) {
        this.facet = facet;
    }

    @Override
    public Tag<DivTag> tag() {
        return div(
                h4("Comments"),
                facet.tag()
        );
    }
}
