package com.iw;

import org.junit.Before;

public abstract class AbsContainerTest {

    protected String username;
    protected String password;

    @Before
    public void initialization() {
        username = System.getenv("username");
        password = System.getenv("password");
    }
}
