package com.iw.facet;

import com.iw.Facet;
import com.iw.Issue;
import j2html.tags.Tag;
import j2html.tags.specialized.MainTag;

import java.util.List;

import static j2html.TagCreator.*;

public final class IssuesFacet implements Facet<MainTag> {

    private final List<Issue> issues;

    public IssuesFacet(List<Issue> issues) {
        this.issues = issues;
    }

    @Override
    public Tag<MainTag> tag() {
        return main(each(issues, (i, e) -> {
            final String title = e.title();
            return article(
                    h2(a(title).withHref("/issue/" + e.id()))
            );
        }));
    }
}