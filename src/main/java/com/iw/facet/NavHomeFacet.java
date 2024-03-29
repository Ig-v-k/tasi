package com.iw.facet;

import com.iw.Facet;
import j2html.tags.Tag;
import j2html.tags.specialized.NavTag;

import static j2html.TagCreator.a;
import static j2html.TagCreator.nav;

public final class NavHomeFacet implements Facet<NavTag> {
    @Override
    public Tag<NavTag> tag() {
        return nav(
                a("Home").withHref("/"),
                a("Github").withHref("https://github.com/Ig-v-k/tasi"));
    }
}
