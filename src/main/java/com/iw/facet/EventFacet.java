package com.iw.facet;

import com.iw.Comments;
import com.iw.Facet;
import j2html.TagCreator;
import j2html.tags.Tag;
import j2html.tags.specialized.MainTag;

public final class EventFacet implements Facet<MainTag> {

    private final Comments comments;

    public EventFacet(Comments comments) {
        this.comments = comments;
    }

    @Override
    public Tag<MainTag> tag() {
        return TagCreator.main();
    }
}
