package com.iw;

import java.util.List;

public interface Comments {
    List<Comment> all();
    boolean add(int event, String text);
}