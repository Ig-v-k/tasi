package com.iw.facet;

import com.iw.Facet;
import j2html.tags.DomContent;
import j2html.tags.Tag;
import j2html.tags.specialized.MainTag;

import static j2html.TagCreator.*;

public final class CommentActionsFacet implements Facet<MainTag> {

    private final Facet<? extends Tag<?>> facet;

    public CommentActionsFacet(Facet<? extends Tag<?>> facet) {
        this.facet = facet;
    }

    @Override
    public Tag<MainTag> tag() {
        return main(commentDlg(), facet.tag(), ftr());
    }

    private static DomContent commentDlg() {
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
