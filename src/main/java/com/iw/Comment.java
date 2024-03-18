package com.iw;

public interface Comment {
    int id();

    int issue();

    User reporter();

    long submit();

    String summary();

    String text();

    boolean update(int issue, String summary, String text);

    boolean delete();
}
