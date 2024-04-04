package com.iw;

import java.util.List;

public interface Issues {
    List<Issue> all();

    Issue bySummary(final String title);

    boolean add(String title);
}
