package com.iw.page;

import com.iw.Events;
import com.iw.Page;
import com.iw.facet.ActionsFacet;
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
                new ActionsFacet(
                        new EventsFacet(events.all()))).render();
    }
}
