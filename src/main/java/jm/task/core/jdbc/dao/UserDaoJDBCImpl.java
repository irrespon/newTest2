package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static String url = "jdbc:mysql://localhost:3306/mydb";
    private static String userName = "test";
    private static String pass = "secret";
    private String userTable = "userTable";
    private Statement statement;

    {
        try {
            statement = Util.connectToDB(url, userName, pass);
        } catch (ClassNotFoundException | SQLException e) {
            if (e.getClass().equals(ClassNotFoundException.class)) {
                System.err.println("не удалось настроить драйвер");
            } else {
                System.err.println("не удалось подключиться к базе данных");
            }
        }
    }

    private String newSqlTable = "Create table " +
            userTable +
            " (id int(10) not null auto_increment, " +
            " name Varchar(50), " +
            " lastname Varchar(50), " +
            " age int not null, " +
            " primary key (id))";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try {
            statement.executeUpdate(newSqlTable);
        } catch (SQLException e) {
            System.err.println("Не удалось создать новую таблицу");
        }
    }

    public void dropUsersTable() {

        try {
            statement.executeUpdate("drop table userTable");
        } catch (SQLException e) {
            System.err.println("не удалось удалить таблицу");
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try {
            statement.executeUpdate("INSERT INTO " + userTable + "(name, lastname, age) VALUES ('" +
                    name + "'" + ", " + "'" +
                    lastName + "'" + ", " +
                    age + ")");
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.err.println("не удалось добавить строку в таблицу " + userTable);
        }
    }

    public void removeUserById(long id) {

        try {
            statement.executeUpdate("Delete from " + userTable + " where id = " + id);
        } catch (SQLException e) {
            System.err.println("не удалось удалить пользователя с id = " + id);
        }
    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM userTable");
            while (resultSet.next()) {
                list.add(new User(resultSet.getString("name"), resultSet.getString("lastname"), resultSet.getByte("age")));
            }
        } catch (SQLException e) {
            System.err.println("не удалось получить список пользователей");
        }
        System.out.println(list);
        return list;
    }

    public void cleanUsersTable() {

        try {
            statement.executeUpdate("Delete from " + userTable);
        } catch (SQLException e) {
            System.err.println("не удалось очистить таблицу " + userTable);
        }
    }
}
