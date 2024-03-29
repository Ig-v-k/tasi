package com.iw.facet;

import com.iw.Facet;
import j2html.tags.DomContent;
import j2html.tags.Tag;
import j2html.tags.specialized.MainTag;

import static j2html.TagCreator.*;

public final class IssuesActionsFacet implements Facet<MainTag> {

    private final Facet<? extends Tag<?>> facet;

    public IssuesActionsFacet(Facet<? extends Tag<?>> facet) {
        this.facet = facet;
    }

    @Override
    public Tag<MainTag> tag() {
        return main(actions(), facet.tag());
    }

    private DomContent actions() {
        return div(
                input().withPlaceholder("Search").withType("text").withSize("20"),
                select(
                        option("Type").withValue(""),
                        option("Bug").withValue("bug"),
                        option("Feature").withValue("feature")),
                select(
                        option("Status").withValue(""),
                        option("Progress").withValue("progress"),
                        option("Todo").withValue("todo")),
                select(
                        option("Assignee").withValue(""),
                        option("A a").withValue("@a"),
                        option("B b").withValue("@b")),
                text(" | "),
                a("Reset").withHref("#"));
    }
}
