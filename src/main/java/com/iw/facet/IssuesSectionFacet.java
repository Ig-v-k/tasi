package com.iw.facet;

import com.iw.Facet;
import j2html.tags.Tag;
import j2html.tags.specialized.DivTag;

import java.util.Arrays;
import java.util.List;

import static j2html.TagCreator.*;

public final class IssuesSectionFacet implements Facet<DivTag> {

    private final List<Facet<? extends Tag<?>>> facets;

    public IssuesSectionFacet(Facet<? extends Tag<?>>... facets) {
        this(Arrays.asList(facets));
    }

    public IssuesSectionFacet(List<Facet<? extends Tag<?>>> facets) {
        this.facets = facets;
    }

    @Override
    public Tag<DivTag> tag() {
        return div(
                span("Admin"),
                h3("Open issues").withStyle("margin-top: 0;"),
                div(
                        a("Share").withHref("#"),
                        text(" • "),
                        a("Export issues").withHref("#")),
                each(facets, f -> f.tag()));
    }
}
