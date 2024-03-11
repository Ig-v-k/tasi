package com.iw;

import java.util.List;

public interface Events {
    List<Event> all();
    Event byTitle(final String title);
    boolean add(String title);
}
