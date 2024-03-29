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
                div(
                        join(
                                a("Report").withHref("#"),
                                "&ensp;",
                                a(b("1"), text("Watch")).withHref("#"),
                                "&ensp;",
                                a(b("2"), text("Likes")).withHref("#"),
                                "&ensp;",
                                a("Share").withHref("#")
                        )
                ),
                div(
                        join(
                                button(attrs(".secondary"), "Attach"),
                                "&nbsp",
                                button(attrs(".secondary"), "Subtask"),
                                "&nbsp",
                                button(attrs(".secondary"), "Link issue"),
                                "&nbsp",
                                button(attrs(".secondary"), "...")
                        )
                ),
                div(
                        select(
                                option("Done").withValue("done"),
                                option("Todo").withValue("todo"),
                                option("In progress").withValue("inprogress")
                        ),
                        select(
                                option("Actions").withValue(""),
                                option("Action1").withValue("action1"),
                                option("Action2").withValue("action2"),
                                option("Action3").withValue("action3")
                        )
                )
        );
    }

    private DialogTag confirmDeleteDlg(final Issue issue) {
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

    private DialogTag editDlg(final Issue issue) {
        return dialog(
                p(b("Edit issue")),
                form(
                        input()
                                .withType("text")
                                .withId("title")
                                .withName("title")
                                .withValue(issue.title().trim())
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
