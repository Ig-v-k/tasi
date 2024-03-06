package com.iw.page;

import com.iw.Page;

public final class TestPage implements Page {
    @Override
    public String render() {
        return "test";
    }
}
