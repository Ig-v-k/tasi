package com.iw.page;

import com.iw.Page;
import com.iw.facet.BodyFacet;
import com.iw.facet.EmptyFacet;
import com.iw.facet.HomeActionsFacet;

public final class HomePage implements Page {
    @Override
    public String render() {
        return new TmplPage(
                "tasi",
                "tasi",
                "Bug report manager",
                new HomeActionsFacet(),
                new BodyFacet(new EmptyFacet())).render();
    }
}
