package com.iw;

import java.util.List;

public interface Events {
    List<Event> all();
    boolean add(String title);
}
