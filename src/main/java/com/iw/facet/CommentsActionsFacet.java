package com.iw.facet;

import com.iw.Facet;
import j2html.tags.DomContent;
import j2html.tags.Tag;
import j2html.tags.specialized.MainTag;

import static j2html.TagCreator.*;

public final class CommentsActionsFacet implements Facet<MainTag> {

    private final Facet<? extends Tag<?>> facet;
    private final int issue;

    public CommentsActionsFacet(final int issue, Facet<? extends Tag<?>> facet) {
        this.facet = facet;
        this.issue = issue;
    }

    private static DomContent addCommentDlg(final int issue) {
        return dialog(
                p(b("Comment")),
                form(
                        input()
                                .withType("text")
                                .withId("summary")
                                .withName("summary")
                                .withPlaceholder("Summary")
                                .withMaxlength("255")
                                .withSize("10")
                                .isRequired(),
                        input()
                                .withType("text")
                                .withId("text")
                                .withName("text")
                                .withPlaceholder("Text")
                                .withMaxlength("255")
                                .withSize("10")
                                .isRequired(),
                        input()
                                .withType("hidden")
                                .withId("issue")
                                .withName("issue")
                                .withValue(String.valueOf(issue)),
                        button("Save").withType("submit")
                ).withAction("/comment/add").withMethod("post"),
                form(
                        button("Close")
                ).withMethod("dialog")
        ).withId("addCommentDlg");
    }

    private static DomContent editCommentDlg(final int issue) {
        return dialog(
                p(b("Edit comment")),
                form(
                        input()
                                .withType("text")
                                .withId("summary")
                                .withName("summary")
                                .withPlaceholder("Summary")
                                .withMaxlength("255")
                                .withSize("10")
                                .isRequired(),
                        input()
                                .withType("text")
                                .withId("text")
                                .withName("text")
                                .withPlaceholder("Text")
                                .withMaxlength("255")
                                .withSize("10")
                                .isRequired(),
                        input()
                                .withType("hidden")
                                .withId("issue")
                                .withName("issue")
                                .withValue(String.valueOf(issue)),
                        input()
                                .withType("hidden")
                                .withId("comment")
                                .withName("comment")
                                .withValue("-1"),
                        button("Save").withType("submit")
                ).withAction("/comment/update").withMethod("post"),
                form(
                        button("Close")
                ).withMethod("dialog")
        ).withId("editCommentDlg");
    }

    private static DomContent confirmDeleteCommentDlg(final int issue) {
        return dialog(
                p(b("Delete comment")),
                p().withId("text"),
                form(
                        input()
                                .withType("hidden")
                                .withId("summary")
                                .withName("summary")
                                .withValue(""),
                        input()
                                .withType("hidden")
                                .withId("issue")
                                .withName("issue")
                                .withValue(String.valueOf(issue)),
                        input()
                                .withType("hidden")
                                .withId("comment")
                                .withName("comment")
                                .withValue("-1"),
                        button("Confirm").withType("submit")
                ).withAction("/comment/delete").withMethod("post"),
                form(
                        button("Close")
                ).withMethod("dialog")
        ).withId("deleteCommentDlg");
    }

    private static DomContent ftr() {
        return button("Add comment").withId("addCommentBottom");
    }

    @Override
    public Tag<MainTag> tag() {
        return main(addCommentDlg(issue), editCommentDlg(issue), confirmDeleteCommentDlg(issue), facet.tag(), ftr());
    }
}
