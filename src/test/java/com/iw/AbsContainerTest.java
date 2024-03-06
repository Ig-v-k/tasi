package com.iw;

import com.iw.container.SqlContainer;
import com.iw.jdbc.PsqlJDBC;
import org.junit.Before;

public abstract class AbsContainerTest {

    protected Container container;
    protected String username;
    protected String password;

    @Before
    public void initialization() {
        username = System.getenv("username");
        password = System.getenv("password");
        container = new SqlContainer(new PsqlJDBC(username, password));
    }
}
