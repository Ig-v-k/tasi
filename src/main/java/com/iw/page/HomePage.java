package com.iw.page;

import com.iw.Container;
import com.iw.Page;
import com.iw.container.SqlContainer;
import com.iw.event.SqlEvent;
import com.iw.facet.EventsFacet;
import com.iw.jdbc.PsqlJDBC;

import java.util.ArrayList;

public final class HomePage implements Page {

    private final Container container;

    public HomePage(Container container) {
        this.container = container;
    }

    @Override
    public String render() {
        return new TmplPage(
                "tasi",
                "tasi",
                "Bug report manager",
                new EventsFacet(new ArrayList<>(){{
                    add(new SqlEvent(container, 1));
                }})).render();
    }
}
