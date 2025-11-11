package com.iw.page;

import com.iw.Issues;
import com.iw.Page;
import com.iw.facet.*;

public final class IssuesPage implements Page {

    private final Issues issues;

    public IssuesPage(final Issues issues) {
        this.issues = issues;
    }

    @Override
    public String render() {
        return new TmplPage(
                "Issues",
                "Issues",
                "List of issues",
                new NavHomeFacet(),
                new BodyFacet("issuesOnLoad()",
                        new IssuesSectionFacet(
                                new IssuesActionsFacet(
                                        new IssuesFacet(issues.all()))))).render();
    }
}
