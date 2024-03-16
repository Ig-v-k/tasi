package com.iw.page;

import com.iw.Container;
import com.iw.Issue;
import com.iw.Page;
import com.iw.comments.RefComments;
import com.iw.facet.*;
import com.iw.issue.SqlIssue;

public final class IssuePage implements Page {

    private final Container container;
    private final int id;

    public IssuePage(Container container, final String id) {
        this(container, Integer.parseInt(id));
    }

    public IssuePage(Container container, int id) {
        this.container = container;
        this.id = id;
    }

    @Override
    public String render() {
        final Issue issue = new SqlIssue(container, id);
        final String title = issue.title();
        return new TmplPage(
                title,
                title,
                String.format("About %s", title),
                new IssueActionsFacet(issue),
                new BodyFacet("issueOnLoad()",
                        new CommentsSectionFacet(
                                new CommentsActionsFacet(id,
                                        new CommentsFacet(
                                                new RefComments(container, id)))))).render();
    }
}
