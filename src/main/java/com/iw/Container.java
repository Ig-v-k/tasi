package com.iw;

import java.sql.Connection;
import java.sql.SQLException;

public interface Container {
    Connection conn() throws SQLException;
}
