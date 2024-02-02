package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/pp_1_1_4";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "my179sql";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
