package com.iw;

public interface Issue {
    int id();

    String title();

    String description();

    boolean update(String title, String description);

    boolean delete();
}
