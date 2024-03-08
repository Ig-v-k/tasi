package com.iw.page;

import com.iw.Container;
import com.iw.Page;

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
                new HomeFacet());
    }
}
