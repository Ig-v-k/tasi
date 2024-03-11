package com.iw.facet;

import com.iw.Facet;
import j2html.tags.DomContent;
import j2html.tags.Tag;
import j2html.tags.specialized.MainTag;

import static j2html.TagCreator.*;

public final class EventActionsFacet implements Facet<MainTag> {

    private final Facet<? extends Tag<?>> facet;

    public EventActionsFacet(Facet<? extends Tag<?>> facet) {
        this.facet = facet;
    }

    @Override
    public Tag<MainTag> tag() {
        return main(createDlg(), actions(), facet.tag());
    }

    private DomContent actions() {
        return div(button("Create").withId("create"));
    }

    private DomContent createDlg() {
        return dialog(
                p(b("Create")),
                form(
                        input()
                                .withType("text")
                                .withId("title")
                                .withName("title")
                                .withPlaceholder("Title")
                                .withMaxlength("255")
                                .withSize("10")
                                .isRequired(),
                        button("Save").withType("submit")
                ).withAction("/events/create").withMethod("post"),
                form(
                        button("Close")
                ).withMethod("dialog")
        ).withId("createDlg");
    }
}
