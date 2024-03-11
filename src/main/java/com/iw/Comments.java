package com.iw;

import java.util.List;

public interface Comments {
    List<Comment> all();
    boolean add(final String summary, String text, int event);
}