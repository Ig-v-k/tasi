package com.iw.page;

import com.iw.Issues;
import com.iw.Page;
import com.iw.facet.*;

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
                        new IssuesSectionFacet(
                                new IssuesActionsFacet(
                                        new IssuesFacet(issues.all()))))).render();
    }
}
