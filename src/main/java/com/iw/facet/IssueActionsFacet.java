package com.iw.facet;

import com.iw.Facet;
import com.iw.Issue;
import j2html.tags.Tag;
import j2html.tags.specialized.DialogTag;
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
                editDlg(issue),
                join(
                        a("Edit")
                                .withHref("#")
                                .withId("editIssue"),
                        " • ",
                        a("Delete")
                                .withHref("#")
                                .withId("deleteIssue")
                )
        );
    }

    private DialogTag confirmDeleteDlg(final Issue issue) {
        return dialog(
                p(b("Edit issue")),
                form(
                        input()
                                .withType("text")
                                .withId("title")
                                .withName("title")
                                .withValue(issue.title())
                                .withPlaceholder("Title")
                                .withMaxlength("255")
                                .withSize("10")
                                .isRequired(),
                        input()
                                .withType("text")
                                .withId("description")
                                .withName("description")
                                .withValue(issue.description())
                                .withPlaceholder("Description")
                                .withMaxlength("255")
                                .withSize("10")
                                .isRequired(),
                        button("Save").withType("submit")
                ).withAction("/issue/update").withMethod("post"),
                form(
                        button("Close")
                ).withMethod("dialog")
        ).withId("editIssueDlg");
    }

    private DialogTag editDlg(final Issue issue) {
        return dialog(
                p(b("Delete issue")),
                p(String.format("Are you sure to delete \"%s\"", issue.title())),
                form(
                        input()
                                .withType("hidden")
                                .withId("title")
                                .withName("title")
                                .withValue(issue.title()),
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
