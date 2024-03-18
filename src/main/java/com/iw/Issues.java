package com.iw;

import java.util.List;

public interface Issues {
    List<Issue> all();

    Issue byTitle(final String title);

    boolean add(String title);
}
