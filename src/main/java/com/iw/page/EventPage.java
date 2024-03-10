package com.iw.page;

import com.iw.Event;
import com.iw.Page;

public final class EventPage implements Page {

    private final Event event;

    public EventPage(Event event) {
        this.event = event;
    }

    @Override
    public String render() {
        final String title = event.title();
        return new TmplPage(
                title,
                title,
                String.format("About %s", title),
                new EventFacet(event));
    }
}
