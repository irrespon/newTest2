package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
       UserService userService = new UserServiceImpl();
       //userService.createUsersTable();
       userService.saveUser("Ivan", "Ivanov", (byte) 9);
        userService.saveUser("Ivan1", "Ivanov1", (byte) 14);
       userService.getAllUsers();
       //userService.dropUsersTable();
       // реализуйте алгоритм здесь
    }
}
