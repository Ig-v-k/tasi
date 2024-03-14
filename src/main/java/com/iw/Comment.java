package com.iw;

public interface Comment {
    int id();

    int issue();

    String summary();
    String text();
    boolean update(int issue, String summary, String text);
    boolean delete();
}
