package com.iw.facet;

import com.iw.Facet;
import j2html.tags.Tag;
import j2html.tags.specialized.BodyTag;

import java.util.Arrays;
import java.util.List;

import static j2html.TagCreator.body;
import static j2html.TagCreator.each;

public final class BodyFacet implements Facet<BodyTag> {

    private final List<Facet<? extends Tag<?>>> facets;
    private final String onLoad;

    public BodyFacet(Facet<? extends Tag<?>>... facets) {
        this("", facets);
    }

    public BodyFacet(String onLoad, Facet<? extends Tag<?>>... facets) {
        this(onLoad, Arrays.asList(facets));
    }

    public BodyFacet(String onLoad, List<Facet<? extends Tag<?>>> facets) {
        this.facets = facets;
        this.onLoad = onLoad;
    }

    @Override
    public Tag<BodyTag> tag() {
        final BodyTag body = body(each(facets, f -> f.tag()));
        if (!onLoad.isEmpty()) {
            body.withOnload(onLoad);
        }
        return body;
    }
}
