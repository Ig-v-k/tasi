package com.iw.facet;

import com.iw.Comment;
import com.iw.Comments;
import com.iw.Facet;
import com.iw.Submit;
import com.iw.submit.TasiSubmit;
import j2html.tags.DomContent;
import j2html.tags.Tag;
import j2html.tags.specialized.DivTag;

import java.util.List;

import static j2html.TagCreator.*;

public final class CommentsFacet implements Facet<DivTag> {

    private final Comments comments;
    private final Submit submit = new TasiSubmit();

    public CommentsFacet(Comments comments) {
        this.comments = comments;
    }

    private static DomContent content(final List<Comment> comments, final Submit submit) {
        if (comments.isEmpty()) {
            return span("Issue comments empty.");
        } else {
            return each(comments, c -> details(
                    summary(String.format("%s • %s", c.reporter().name(), submit.format(c.submit()))),
                    p(b(c.summary())).withClass("summary"),
                    p(c.text()).withClass("text"),
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

    @Override
    public Tag<DivTag> tag() {
        final List<Comment> list = comments.all();
        return div(content(list, submit));
    }
}
