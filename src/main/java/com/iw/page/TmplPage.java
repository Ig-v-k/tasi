package com.iw.page;

import com.iw.Facet;
import com.iw.Page;
import com.iw.facet.EmptyFacet;
import j2html.tags.Tag;
import j2html.tags.specialized.FooterTag;
import j2html.tags.specialized.HeaderTag;
import j2html.tags.specialized.NavTag;

import static j2html.TagCreator.*;

public final class TmplPage implements Page {

    private final Facet<NavTag> navigation;
    private final String title;
    private final String headerTitle;
    private final String headerSubtitle;
    private final Facet<? extends Tag<?>> actions;
    private final Facet<? extends Tag<?>> body;

    public TmplPage(String title, Facet<? extends Tag<?>> body) {
        this(title, "", "", body);
    }

    public TmplPage(String title, String headerTitle, String headerSubtitle, Facet<? extends Tag<?>> body) {
        this(new EmptyFacet(), title, headerTitle, headerSubtitle, new EmptyFacet(), body);
    }

    public TmplPage(String title,
                    String headerTitle,
                    String headerSubtitle,
                    Facet<? extends Tag<?>> actions,
                    Facet<? extends Tag<?>> body) {
        this(new EmptyFacet(), title, headerTitle, headerSubtitle, actions, body);
    }

    public TmplPage(final Facet<NavTag> navigation, String title,
                    String headerTitle,
                    String headerSubtitle,
                    Facet<? extends Tag<?>> actions,
                    Facet<? extends Tag<?>> body) {
        this.navigation = navigation;
        this.title = title;
        this.headerTitle = headerTitle;
        this.headerSubtitle = headerSubtitle;
        this.actions = actions;
        this.body = body;
    }

    @Override
    public String render() {
        return html(
                head(
                        meta().withCharset("UTF-8"),
                        meta().withName("viewport").withContent("width=device-width, initial-scale=1.0"),
                        meta().withName("keywords").withContent("statistic, progress, government"),
                        meta().withName("description").withContent("Country statistic"),
                        title(title.isEmpty() ? "tasi" : title),
                        link().withRel("stylesheet").withHref("/css/simple.min.css"),
                        link().withRel("icon").withType("image/png").withHref("/images/logo/logo_32.png"),
                        script().withType("text/javascript").withSrc("/js/main.js")
                ),
                body(
                        header(
                                navigation.tag(),
                                iffElse(headerTitle.isEmpty(), emptyTag("h1"), h1(title).withId("title")),
                                iffElse(headerSubtitle.isEmpty(), emptyTag("p"), p(headerSubtitle).withId("title")),
                                actions.tag()
                        ),
                        body.tag(),
                        ftr()
                )
        ).render();
    }

    private static FooterTag ftr() {
        return footer(
                p(join("Made by ", a("@Ig-v-k").withHref("https://github.com/Ig-v-k"), ", 2024")),
                nav(a("GitHub").withHref("https://github.com/Ig-v-k/tasi"))
        );
    }
}
