package com.iw.page;

import com.iw.Container;
import com.iw.Event;
import com.iw.Page;
import com.iw.comments.SqlComments;
import com.iw.event.SqlEvent;
import com.iw.facet.BodyFacet;
import com.iw.facet.CommentActionsFacet;
import com.iw.facet.CommentsFacet;

public final class EventPage implements Page {

    private final Container container;
    private final int id;

    public EventPage(Container container, final String id) {
        this(container, Integer.parseInt(id));
    }

    public EventPage(Container container, int id) {
        this.container = container;
        this.id = id;
    }

    @Override
    public String render() {
        final Event event = new SqlEvent(container, id);
        final String title = event.title();
        return new TmplPage(
                title,
                title,
                String.format("About %s", title),
                new BodyFacet(
                        new CommentActionsFacet(
                                new CommentsFacet(
                                        new SqlComments(container)), id), "issueOnLoad()")).render();
    }
}
