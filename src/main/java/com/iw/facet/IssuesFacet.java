package com.iw.facet;

import com.iw.Facet;
import com.iw.Issue;
import j2html.tags.Tag;
import j2html.tags.specialized.TableTag;

import java.util.List;

import static j2html.TagCreator.*;

public final class IssuesFacet implements Facet<TableTag> {

    private final List<Issue> issues;

    public IssuesFacet(List<Issue> issues) {
        this.issues = issues;
    }

    @Override
    public Tag<TableTag> tag() {
        return table(
                tr(
                        th("T"),
                        th("Key"),
                        th("Summary"),
                        th("Assignee"),
                        th("Reporter"),
                        th("P"),
                        th("Status"),
                        th("Resolution"),
                        th("Created"),
                        th("Updated")
                ), each(issues, (i, e) -> {
                    final String title = e.title();
                    return tr(
                            td("-T-"),
                            td("-Key-"),
                            td(title),
                            td("-Assignee-"),
                            td("-Reporter-"),
                            td("-P-"),
                            td("-Status-"),
                            td("-Resolution-"),
                            td("-Created-"),
                            td("-Updated-"));
                })
        );
    }
}