package com.iw.facet;

import static j2html.TagCreator.*;

public final class IssueStatusFacet implements Facet<SelectTag> {
    private final int status;
    private final Container container;

    public IssueStatusFacet(final int status, final Container container) {
        this.status = status;
        this.container = container;
    }

    @Override
    public Tag<SelectTag> tag() {
        final Statuses statuses = new SqlStatuses(container);
        final List<Status> list = statuses.all();
        return select(each(item -> option(item.name()).withValue(item.level()).condAttr(item.id() == status, "selected", "selected")));
    }
}