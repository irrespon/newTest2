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

    private final String userTable = "userTable";

    private Connection connection;

    {
        try {
            connection = Util.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("Create table " +
                    userTable +
                    " (id int(10) not null auto_increment, " +
                    " name Varchar(50), " +
                    " lastname Varchar(50), " +
                    " age int not null, " +
                    " primary key (id))");
            connection.commit();
        } catch (SQLException e) {
            e.getMessage();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {

        try (Statement statement = connection.createStatement())
        {
            statement.executeUpdate("drop table userTable");
            connection.commit();
        } catch (SQLException e) {
            e.getMessage();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (Statement statement = connection.createStatement())
        {
            statement.executeUpdate("INSERT INTO " + userTable + "(name, lastname, age) VALUES ('" +
                    name + "'" + ", " + "'" +
                    lastName + "'" + ", " +
                    age + ")");
            System.out.println("User с именем - " + name + " добавлен в базу данных");
            connection.commit();
        } catch (SQLException e) {
            e.getMessage();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("Delete from " + userTable + " where id = " + id);
            connection.commit();
        } catch (SQLException e) {
            e.getMessage();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM userTable");
            while (resultSet.next()) {
                list.add(new User(resultSet.getString("name"), resultSet.getString("lastname"), resultSet.getByte("age")));
            }
            connection.commit();
        } catch (SQLException e) {
            e.getMessage();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(list);
        return list;
    }

    public void cleanUsersTable() {

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("Delete from " + userTable);
            connection.commit();
        } catch (SQLException e) {
            e.getMessage();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
