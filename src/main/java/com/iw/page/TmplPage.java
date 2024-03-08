package com.iw.page;

import com.iw.Facet;
import com.iw.Page;
import j2html.tags.Tag;
import j2html.tags.specialized.FooterTag;
import j2html.tags.specialized.HeaderTag;

import static j2html.TagCreator.*;

public final class TmplPage implements Page {

    private final String title;
    private final String headerTitle;
    private final String headerSubtitle;
    private final Facet<? extends Tag<?>> facet;

    public TmplPage(String title, Facet<? extends Tag<?>> facet) {
        this(title, "", "", facet);
    }

    public TmplPage(String title, String headerTitle, String headerSubtitle, Facet<? extends Tag<?>> facet) {
        this.title = title;
        this.headerTitle = headerTitle;
        this.headerSubtitle = headerSubtitle;
        this.facet = facet;
    }

    private static HeaderTag hdr(final String title, final String subtitle) {
        final Tag<? extends Tag<?>> h1 = title.isEmpty() ? emptyTag("h1") : h1(title);
        final Tag<? extends Tag<?>> p = subtitle.isEmpty() ? emptyTag("p") : p(subtitle);
        return header(
                nav(
                        a("Home").withHref("/"),
                        a("Github").withHref("https://github.com/Ig-v-k/cstat")),
                h1,
                p
        );
    }

    private static FooterTag ftr() {
        return footer(
                p(join("Made by ", a("@Ig-v-k").withHref("https://github.com/Ig-v-k"), ", 2024")),
                nav(a("GitHub").withHref("https://github.com/Ig-v-k/cstat"))
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
                        title(title.isEmpty() ? "cstat" : title),
                        link().withRel("stylesheet").withHref("/css/simple.min.css"),
                        link().withRel("stylesheet").withHref("/css/style.css"),
                        link().withRel("icon").withType("image/png").withHref("/images/logo/logo_32.png")
                ),
                body(
                        hdr(headerTitle, headerSubtitle),
                        facet.tag(),
                        ftr()
                )
        ).render();
    }
}
