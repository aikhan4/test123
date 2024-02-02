package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoJDBCImpl userDaoJDBCImpl;
    public UserServiceImpl() {
        try {
            this.userDaoJDBCImpl = new UserDaoJDBCImpl(new Util().getConnection());
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }
    public void createUsersTable() {
        userDaoJDBCImpl.createUsersTable();
    }
    public void dropUsersTable() {
        userDaoJDBCImpl.dropUsersTable();
    }
    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBCImpl.saveUser(name, lastName, age);
        System.out.printf("User с именем – %s добавлен в базу данных%n", name);
    }
    public void removeUserById(long id) {
        userDaoJDBCImpl.removeUserById(id);
    }
    public List<User> getAllUsers() {
        return userDaoJDBCImpl.getAllUsers();
    }
    public void cleanUsersTable() {
        userDaoJDBCImpl.cleanUsersTable();
    }
    public void closeConnection() {
        userDaoJDBCImpl.closeConnection();
    }
}
