package com.iw;

import java.util.List;

public interface Comments {
    List<Comment> all();
    List<Comment> byEvent(final int event);
    boolean add(final String summary, String text, int event);
}