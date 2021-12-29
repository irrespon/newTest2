package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    public static Connection connectToDB(String url, String userName, String pass) throws ClassNotFoundException, SQLException {

        return DriverManager.getConnection(url,userName,pass);
    }
}
