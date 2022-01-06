package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static String url = "jdbc:mysql://localhost:3306/mydb";
    private static String userName = "test";
    private static String pass = "secret";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection(url,userName,pass);
        connection.setAutoCommit(false);

        return connection;
    }
}
