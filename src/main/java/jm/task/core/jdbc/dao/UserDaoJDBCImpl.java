package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final String url = "jdbc:mysql://localhost:3306/mydb";
    private final String userName = "test";
    private final String pass = "secret";
    private final String userTable = "userTable";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Statement statement = Util.connectToDB(url, userName, pass).createStatement()) {
            statement.executeUpdate("Create table " +
                    userTable +
                    " (id int(10) not null auto_increment, " +
                    " name Varchar(50), " +
                    " lastname Varchar(50), " +
                    " age int not null, " +
                    " primary key (id))");
        } catch (SQLException | ClassNotFoundException e) {
            e.getMessage();
        }
    }

    public void dropUsersTable() {

        try (Statement statement = Util.connectToDB(url, userName, pass).createStatement())
        {
            statement.executeUpdate("drop table userTable");
        } catch (SQLException | ClassNotFoundException e) {
            e.getMessage();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (Statement statement = Util.connectToDB(url, userName, pass).createStatement())
        {
            statement.executeUpdate("INSERT INTO " + userTable + "(name, lastname, age) VALUES ('" +
                    name + "'" + ", " + "'" +
                    lastName + "'" + ", " +
                    age + ")");
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException | ClassNotFoundException e) {
            e.getMessage();
        }
    }

    public void removeUserById(long id) {

        try (Statement statement = Util.connectToDB(url, userName, pass).createStatement()) {
            statement.executeUpdate("Delete from " + userTable + " where id = " + id);
        } catch (SQLException | ClassNotFoundException e) {
            e.getMessage();
        }
    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();

        try(Statement statement = Util.connectToDB(url, userName, pass).createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM userTable");
            while (resultSet.next()) {
                list.add(new User(resultSet.getString("name"), resultSet.getString("lastname"), resultSet.getByte("age")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.getMessage();
        }
        System.out.println(list);
        return list;
    }

    public void cleanUsersTable() {

        try (Statement statement = Util.connectToDB(url, userName, pass).createStatement()) {
            statement.executeUpdate("Delete from " + userTable);
        } catch (SQLException | ClassNotFoundException e) {
            e.getMessage();
        }
    }
}
