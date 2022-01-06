package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
//        userService.saveUser("Ivan", "Ivanov", (byte) 9);
//        userService.saveUser("Ivan1", "Ivanov1", (byte) 14);
//        userService.saveUser("Ivan2", "Ivanov2", (byte) 23);
//        userService.saveUser("Ivan3", "Ivanov3", (byte) 55);
//        userService.getAllUsers();
//        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
