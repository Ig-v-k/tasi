package com.iw.facet;

import com.iw.Facet;
import com.iw.Issue;
import j2html.tags.Tag;
import j2html.tags.specialized.DivTag;
import j2html.tags.specialized.SectionTag;

import static j2html.TagCreator.*;

public final class IssueDetailsFacet implements Facet<DivTag> {

    private final Issue issue;

    public IssueDetailsFacet(Issue issue) {
        this.issue = issue;
    }

    @Override
    public Tag<DivTag> tag() {
        return div(
                h4("Details"),
                p(issue.description()),
                div(
                        join(
                                span(b("Assignee:")), span("Admin").withId("assignee"),
                                "&ensp;",
                                span(b("Components:")), span("launch, rocket").withId("components"),
                                "&ensp;",
                                span(b("Labels:")), span("black-hole, earth, outer-space").withId("labels"),
                                "&ensp;",
                                span(b("Reporter:")), span("Admin").withId("reporter"),
                                "&ensp;",
                                span(b("Priority:")), span("High").withId("priority"),
                                "&ensp;",
                                span(b("Project:")), span("CRM").withId("project"),
                                "&ensp;",
                                span(b("Time tracking:")), span("2w 3d 4h 48m logged")
                        )
                )
        ).withId("details");
    }
}
