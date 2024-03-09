package com.iw.facet;

import com.iw.Event;
import com.iw.Facet;
import j2html.tags.Tag;
import j2html.tags.specialized.MainTag;

import java.util.List;

import static j2html.TagCreator.*;

public final class EventsFacet implements Facet<MainTag> {

    private final List<Event> events;

    public EventsFacet(List<Event> events) {
        this.events = events;
    }

    @Override
    public Tag<MainTag> tag() {
        return main(table(each(events, e -> tr(
                td(e.title())
        ))));
    }
}