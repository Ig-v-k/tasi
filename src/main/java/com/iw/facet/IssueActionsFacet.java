package com.iw.facet;

import com.iw.Facet;
import com.iw.Issue;
import j2html.tags.DomContent;
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
                confirmDeleteDlg(issue),
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

    private DomContent confirmDeleteDlg(final Issue issue) {
        return dialog(
                p(b("Delete issue")),
                p().withId("text"),
                form(
                        input()
                                .withType("hidden")
                                .withId("title")
                                .withName("title")
                                .withValue(""),
                        input()
                                .withType("hidden")
                                .withId("issue")
                                .withName("issue")
                                .withValue(String.valueOf(issue.id())),
                        button("Confirm").withType("submit")
                ).withAction("/issue/delete").withMethod("post"),
                form(
                        button("Close")
                ).withMethod("dialog")
        ).withId("deleteIssueDlg");
    }
}
