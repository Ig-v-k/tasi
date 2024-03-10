package com.iw.page;

import com.iw.Container;
import com.iw.Events;
import com.iw.Page;
import com.iw.events.SqlEvents;
import com.iw.facet.ActionsFacet;
import com.iw.facet.EventsFacet;

public final class HomePage implements Page {

    private final Container container;

    public HomePage(Container container) {
        this.container = container;
    }

    @Override
    public String render() {
        final Events events = new SqlEvents(container);
        return new TmplPage(
                "tasi",
                "tasi",
                "Bug report manager",
                new ActionsFacet(
                        new EventsFacet(events.all()))).render();
    }
}
