package com.iw.facet;

import com.iw.Event;
import com.iw.Facet;
import j2html.tags.Tag;
import j2html.tags.specialized.MainTag;

public final class EventFacet implements Facet<MainTag> {

    private final Event event;

    public EventFacet(Event event) {
        this.event = event;
    }

    @Override
    public Tag<MainTag> tag() {
        return main();
    }
}
