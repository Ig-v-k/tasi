package com.iw.facet;

import com.iw.Facet;
import j2html.tags.DomContent;
import j2html.tags.Tag;
import j2html.tags.specialized.MainTag;

import static j2html.TagCreator.*;

public final class CommentActionsFacet implements Facet<MainTag> {

    private final Facet<? extends Tag<?>> facet;
    private final int issue;

    public CommentActionsFacet(final int issue, Facet<? extends Tag<?>> facet) {
        this.facet = facet;
        this.issue = issue;
    }

    @Override
    public Tag<MainTag> tag() {
        return main(commentDlg(issue), facet.tag(), ftr());
    }

    private static DomContent commentDlg(final int issue) {
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
                ).withAction("/comments/add").withMethod("post"),
                form(
                        button("Close")
                ).withMethod("dialog")
        ).withId("commentDlg");
    }

    private static DomContent ftr() {
        return button("Add").withId("add");
    }
}
