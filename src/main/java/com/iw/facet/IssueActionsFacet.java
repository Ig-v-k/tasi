package com.iw.facet;

import com.iw.Facet;
import com.iw.Issue;
import j2html.tags.Tag;
import j2html.tags.specialized.DivTag;

import static j2html.TagCreator.*;

public final class IssueActionsFacet implements Facet<DivTag> {

    private final Issue issue;

    public IssueActionsFacet(Issue issue) {
        this.issue = issue;
    }

    @Override
    public Tag<DivTag> tag() {
        return div(
                join(
                        a("Edit")
                                .withHref("#")
                                .withClass("editIssue"),
                        " • ",
                        a("Delete")
                                .withHref("#")
                                .withClass("deleteIssue")
                )
        );
    }
}
