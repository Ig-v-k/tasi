package com.iw.facet;

import com.iw.Facet;
import j2html.TagCreator;
import j2html.tags.Tag;
import j2html.tags.specialized.BodyTag;

import static j2html.TagCreator.body;

public final class IssueBodyFacet implements Facet<BodyTag> {

    private final Facet<? extends Tag<?>> facet;

    public IssueBodyFacet(Facet<? extends Tag<?>> facet) {
        this.facet = facet;
    }

    @Override
    public Tag<BodyTag> tag() {
        return body(facet.tag()).withOnload("issueOnLoad()");
    }
}
