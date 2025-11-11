package com.iw.facet;

import com.iw.Facet;
import j2html.tags.DomContent;
import j2html.tags.Tag;
import j2html.tags.specialized.DivTag;

import static j2html.TagCreator.*;

public final class IssuesActionsFacet implements Facet<DivTag> {

    private final Facet<? extends Tag<?>> facet;

    public IssuesActionsFacet(Facet<? extends Tag<?>> facet) {
        this.facet = facet;
    }

    @Override
    public Tag<DivTag> tag() {
        return div(actions(), facet.tag());
    }

    private DomContent actions() {
        return form(
                input().withId("search").withName("search").withPlaceholder("Search").withType("text").withSize("20"),
                select(
                        option("Type").withValue(""),
                        option("Bug").withValue("bug"),
                        option("Feature").withValue("feature")
                ).withId("type").withName("type"),
                select(
                        option("Status").withValue(""),
                        option("Progress").withValue("progress"),
                        option("Todo").withValue("todo")
                ).withId("status").withName("status"),
                select(
                        option("Assignee").withValue(""),
                        option("A a").withValue("a_a"),
                        option("B b").withValue("b_b")
                ).withId("assignee").withName("assignee"),
                button("Search").withId("search").withType("submit"),
                text(" | "),
                a("Reset").withHref("/")
        ).withAction("/").withMethod("get");
    }
}
