package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
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
   // private String userRaw =
    private Connection connection = Util.connectToDB(url,userName,pass);
    private Statement statement;

    {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String newSqlTable = "Create table " +
            userTable +
            " (id int(10) not null auto_increment, " +
            " name Varchar(50), " +
            " lastname Varchar(50), " +
            " age int not null, " +
            " primary key (id))";
    private String deleteTable = "drop table userTable";
    private String sql = "SELECT * FROM userTable";

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
            statement.executeUpdate(deleteTable);
        } catch (SQLException e) {
            System.err.println("не удалось удалить таблицу");
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        String userRaw = "INSERT INTO " + userTable + "(name, lastname, age) VALUES ('" + name + "'" +  ", " + "'" + lastName + "'" + ", " + age + ")";
        try {
            System.out.println(userRaw);
            statement.executeUpdate(userRaw);
            connection.commit();
        } catch (SQLException e) {
            System.err.println("не удалось добавить строку в таблицу " + userTable);
        }

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new User(resultSet.getString("name"),resultSet.getString("lastname"), resultSet.getByte("age")));
            }
        } catch (SQLException e) {
            System.err.println("не удалось получить список пользователей");
        }
        return list;
    }

    public void cleanUsersTable() {

    }
}
