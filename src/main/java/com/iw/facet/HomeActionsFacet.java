package com.iw.facet;

import com.iw.Facet;
import j2html.tags.DomContent;
import j2html.tags.Tag;
import j2html.tags.specialized.DivTag;

import static j2html.TagCreator.*;
import static j2html.TagCreator.button;

public final class HomeActionsFacet implements Facet<DivTag> {
    @Override
    public Tag<DivTag> tag() {
        return div(
                createDlg(),
                select(
                        option("Projects").withValue(""),
                        option("P1").withValue("p1"),
                        option("P2").withValue("p2")),
                select(
                        option("Filters").withValue(""),
                        option("F1").withValue("f1"),
                        option("F2").withValue("f2")),
                select(
                        option("Dashboards").withValue(""),
                        option("D1").withValue("d1"),
                        option("D2").withValue("d2")),
                button("Create").withId("create"));
    }

    private DomContent createDlg() {
        return dialog(
                p(b("Create")),
                form(
                        input()
                                .withType("text")
                                .withId("summary")
                                .withName("summary")
                                .withPlaceholder("Summary")
                                .withMaxlength("255")
                                .withSize("10")
                                .isRequired(),
                        button("Save").withType("submit")
                ).withAction("/issue/create").withMethod("post"),
                form(
                        button("Close")
                ).withMethod("dialog")
        ).withId("createDlg");
    }
}
