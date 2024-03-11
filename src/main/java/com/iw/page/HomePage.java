package com.iw.page;

import com.iw.Events;
import com.iw.Page;
import com.iw.facet.BodyFacet;
import com.iw.facet.EventActionsFacet;
import com.iw.facet.EventsFacet;

public final class HomePage implements Page {

    private final Events events;

    public HomePage(final Events events) {
        this.events = events;
    }

    @Override
    public String render() {
        return new TmplPage(
                "tasi",
                "tasi",
                "Bug report manager",
                new BodyFacet("eventsOnLoad()",
                        new EventActionsFacet(
                                new EventsFacet(events.all())))).render();
    }
}
