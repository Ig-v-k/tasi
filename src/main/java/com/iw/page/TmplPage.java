package com.iw.page;

import com.iw.Facet;
import com.iw.Page;
import com.iw.facet.EmptyFacet;
import j2html.tags.Tag;
import j2html.tags.specialized.FooterTag;
import j2html.tags.specialized.HeaderTag;

import static j2html.TagCreator.*;

public final class TmplPage implements Page {

    private final String title;
    private final String headerTitle;
    private final String headerSubtitle;
    private final Facet<? extends Tag<?>> actions;
    private final Facet<? extends Tag<?>> body;

    public TmplPage(String title, Facet<? extends Tag<?>> body) {
        this(title, "", "", body);
    }

    public TmplPage(String title,
                    String headerTitle,
                    String headerSubtitle,
                    Facet<? extends Tag<?>> body) {
        this(title, headerTitle, headerSubtitle, new EmptyFacet(), body);
    }

    public TmplPage(String title,
                    String headerTitle,
                    String headerSubtitle,
                    Facet<? extends Tag<?>> actions,
                    Facet<? extends Tag<?>> body) {
        this.title = title;
        this.headerTitle = headerTitle;
        this.headerSubtitle = headerSubtitle;
        this.actions = actions;
        this.body = body;
    }

    private static HeaderTag hdr(final String title, final String subtitle, final Facet<? extends Tag<?>> actions) {
        final Tag<? extends Tag<?>> h1 = title.isEmpty() ? emptyTag("h1") : h1(title).withId("title");
        final Tag<? extends Tag<?>> p = subtitle.isEmpty() ? emptyTag("p") : p(subtitle).withId("subtitle");
        return header(
                nav(
                        a("Home").withHref("/"),
                        a("Github").withHref("https://github.com/Ig-v-k/tasi")),
                h1, p, actions.tag()
        );
    }

    private static FooterTag ftr() {
        return footer(
                p(join("Made by ", a("@Ig-v-k").withHref("https://github.com/Ig-v-k"), ", 2024")),
                nav(a("GitHub").withHref("https://github.com/Ig-v-k/tasi"))
        );
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
                        hdr(headerTitle, headerSubtitle, actions),
                        body.tag(),
                        ftr()
                )
        ).render();
    }
}
