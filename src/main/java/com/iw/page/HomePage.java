package com.iw.page;

import com.iw.Issues;
import com.iw.Page;
import com.iw.facet.BodyFacet;
import com.iw.facet.IssuesActionsFacet;
import com.iw.facet.IssuesFacet;

public final class HomePage implements Page {

    private final Issues issues;

    public HomePage(final Issues issues) {
        this.issues = issues;
    }

    @Override
    public String render() {
        return new TmplPage(
                "tasi",
                "tasi",
                "Bug report manager",
                new BodyFacet("issuesOnLoad()",
                        new IssuesActionsFacet(
                                new IssuesFacet(issues.all())))).render();
    }
}
