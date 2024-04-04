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
    long resolved();
    boolean update(final String summary,
                   final String description,
                   final int assignee,
                   final int reporter,
                   final int priority,
                   final int status,
                   final long created,
                   final long updated,
                   final long resolved);
    boolean delete();
}
