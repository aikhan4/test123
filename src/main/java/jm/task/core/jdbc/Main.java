package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Alex", "Lew", (byte) 20);
        userService.saveUser("Max", "Hope", (byte) 23);
        userService.saveUser("Daniel", "New", (byte) 17);
        userService.saveUser("Mark", "Grew", (byte) 42);
        userService.removeUserById(1);
        List<User> users = userService.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
        userService.closeConnection(); // ... well
    }

}