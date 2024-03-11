package com.iw.facet;

import com.iw.Event;
import com.iw.Facet;
import j2html.tags.DomContent;
import j2html.tags.Tag;
import j2html.tags.specialized.MainTag;

import static j2html.TagCreator.*;

public final class CommentActionsFacet implements Facet<MainTag> {

    private final Facet<? extends Tag<?>> facet;
    private final int event;

    public CommentActionsFacet(Facet<? extends Tag<?>> facet, final int event) {
        this.facet = facet;
        this.event = event;
    }

    @Override
    public Tag<MainTag> tag() {
        return main(commentDlg(event), facet.tag(), ftr());
    }

    private static DomContent commentDlg(final int event) {
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
                                .withId("event")
                                .withName("event")
                                .withValue(String.valueOf(event)),
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
