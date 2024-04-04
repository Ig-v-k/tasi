package com.iw;

public interface Issue {
    int id();
    String summary();
    String description();
    int assignee();
    int reporter();
    int priority();
    int status();
    long created();
    long updated();
    boolean update(String title, String description);
    boolean delete();
}
