package com.iw;

import java.util.List;

public interface Logs {
    List<Log> list();
    boolean add(String text);
}
