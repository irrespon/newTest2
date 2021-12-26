package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    public static Connection connectToDB(String url, String userName, String pass) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Ошибка подключения к MySQL");
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,userName,pass);
        } catch (SQLException e) {
            System.err.println("ошибка подключения к " + url);;
        }

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.getMessage();
        }

        return connection;
    }



    // реализуйте настройку соеденения с БД
}
