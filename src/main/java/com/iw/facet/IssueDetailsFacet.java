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
                p(issue.description())
        );
    }
}
