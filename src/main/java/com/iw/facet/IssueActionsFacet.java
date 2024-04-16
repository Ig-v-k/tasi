package com.iw.facet;

import com.iw.Facet;
import com.iw.Issue;
import j2html.tags.Tag;
import j2html.tags.specialized.DialogTag;
import j2html.tags.specialized.DivTag;

import static j2html.TagCreator.*;

public final class IssueActionsFacet implements Facet<DivTag> {

    private final Issue issue;
    private final Container container;

    public IssueActionsFacet(final Issue issue, final Container container) {
        this.issue = issue;
        this.container = container;
    }

    @Override
    public Tag<DivTag> tag() {
        return div(
                deleteDlg(issue), editDlg(issue),
                div(
                        join(new IssueStatusFacet(issue.status(), container).tag(),
                                "&nbsp",
                                button(attrs(".secondary"), "Edit"),
                                "&nbsp",
                                button(attrs(".secondary"), "Delete"),
                                "&nbsp"
                        )
                ),
                div(
                        join(
                                a(b("1"), text("Watch")).withHref("#"),
                                "&ensp;",
                                a(b("2"), text("Likes")).withHref("#"),
                                "&ensp;",
                                a("Share").withHref("#")
                        )
                )        );
    }

    private DialogTag deleteDlg(final Issue issue) {
        return dialog(
                p(b("Delete issue")),
                p(String.format("Are you sure to delete \"%s\"", issue.summary())),
                form(
                        input()
                                .withType("hidden")
                                .withId("title")
                                .withName("title")
                                .withValue(issue.summary()),
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

    private DialogTag editDlg(final Issue issue) {
        return dialog(
                p(b("Edit issue")),
                form(
                        input()
                                .withType("text")
                                .withId("title")
                                .withName("title")
                                .withValue(issue.summary().trim())
                                .withPlaceholder("Title")
                                .withMaxlength("255")
                                .withSize("10")
                                .isRequired(),
                        input()
                                .withType("text")
                                .withId("description")
                                .withName("description")
                                .withValue(issue.description().trim())
                                .withPlaceholder("Description")
                                .withMaxlength("255")
                                .withSize("10")
                                .isRequired(),
                        input()
                                .withType("hidden")
                                .withId("issue")
                                .withName("issue")
                                .withValue(String.valueOf(issue.id())),
                        button("Confirm").withType("submit")
                ).withAction("/issue/update").withMethod("post"),
                form(
                        button("Close")
                ).withMethod("dialog")
        ).withId("editIssueDlg");
    }
}
