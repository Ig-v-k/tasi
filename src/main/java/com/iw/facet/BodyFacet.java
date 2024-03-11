package com.iw.facet;

import com.iw.Facet;
import j2html.TagCreator;
import j2html.tags.Tag;
import j2html.tags.specialized.BodyTag;

public final class BodyFacet implements Facet<BodyTag> {

    private final Facet<? extends Tag<?>> facet;
    private final String onLoad;

    public BodyFacet(Facet<? extends Tag<?>> facet) {
        this(facet, "");
    }

    public BodyFacet(Facet<? extends Tag<?>> facet, String onLoad) {
        this.facet = facet;
        this.onLoad = onLoad;
    }

    @Override
    public Tag<BodyTag> tag() {
        final BodyTag body = TagCreator.body(facet.tag());
        if (!onLoad.isEmpty()) {
            body.withOnload(onLoad);
        }
        return body;
    }
}
