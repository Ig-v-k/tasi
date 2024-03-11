package com.iw.facet;

import com.iw.Comment;
import com.iw.Comments;
import com.iw.Facet;
import j2html.tags.DomContent;
import j2html.tags.Tag;
import j2html.tags.specialized.MainTag;

import java.util.List;

import static j2html.TagCreator.*;

public final class EventFacet implements Facet<MainTag> {

    private final Comments comments;

    public EventFacet(Comments comments) {
        this.comments = comments;
    }

    @Override
    public Tag<MainTag> tag() {
        final List<Comment> list = comments.all();
        return main(content(list));
    }

    private static DomContent content(final List<Comment> comments) {
        if (comments.isEmpty()) {
            return span("Issue comments empty.");
        } else {
            return each(comments, c -> details(
                    summary(c.summary()),
                    text(c.text())
            ));
        }
    }
}
