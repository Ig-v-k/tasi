package com.iw.facet;

import com.iw.Comment;
import com.iw.Comments;
import com.iw.Facet;
import j2html.tags.DomContent;
import j2html.tags.Tag;
import j2html.tags.specialized.DivTag;

import java.util.List;

import static j2html.TagCreator.*;

public final class CommentsFacet implements Facet<DivTag> {

    private final Comments comments;

    public CommentsFacet(Comments comments) {
        this.comments = comments;
    }

    @Override
    public Tag<DivTag> tag() {
        final List<Comment> list = comments.all();
        return div(content(list));
    }

    private static DomContent content(final List<Comment> comments) {
        if (comments.isEmpty()) {
            return span("Issue comments empty.");
        } else {
            return each(comments, c -> details(
                    summary(c.summary()),
                    p(c.text()),
                    hr(),
                    footer(join(
                            a("Edit")
                                    .withHref("#")
                                    .withClass("editComment"),
                            " • ",
                            a("Delete")
                                    .withHref("#")
                                    .withClass("deleteComment")
                    ))
            ).withId("comment_" + c.id()).withData("comment", String.valueOf(c.id())));
        }
    }
}
