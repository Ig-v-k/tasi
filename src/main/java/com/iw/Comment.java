package com.iw;

public interface Comment {
    int id();
    int issue();
    int reporter();
    long submit();
    String summary();
    String text();
    boolean update(int issue, String summary, String text);
    boolean delete();
}
