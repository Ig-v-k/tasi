package com.iw.page;

import com.iw.Issue;
import com.iw.Issues;
import com.iw.Page;
import com.iw.facet.*;

import java.util.List;

public final class IssuesPage implements Page {

    private final Issues issues;

    public IssuesPage(final Issues issues) {
        this.issues = issues;
    }

    @Override
    public String render() {
        final List<Issue> all = issues.all();
        return new TmplPage(
                "Issues",
                "Issues",
                "List of issues",
                new NavHomeFacet(),
                new BodyFacet("issuesOnLoad()",
                        new IssuesSectionFacet(
                                new IssuesActionsFacet(
                                        new IssuesFacet(all))))).render();
    }
}
