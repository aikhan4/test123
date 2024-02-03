package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoHibernateImpl UserDaoHibernateImpl;
    public UserServiceImpl() {
        this.UserDaoHibernateImpl = new UserDaoHibernateImpl();
    }
    public void createUsersTable() {
        UserDaoHibernateImpl.createUsersTable();
    }
    public void dropUsersTable() {
        UserDaoHibernateImpl.dropUsersTable();
    }
    public void saveUser(String name, String lastName, byte age) {
        UserDaoHibernateImpl.saveUser(name, lastName, age);
        System.out.printf("User с именем – %s добавлен в базу данных%n", name);
    }
    public void removeUserById(long id) {
        UserDaoHibernateImpl.removeUserById(id);
    }
    public List<User> getAllUsers() {
        return UserDaoHibernateImpl.getAllUsers();
    }
    public void cleanUsersTable() {
        UserDaoHibernateImpl.cleanUsersTable();
    }
    public void closeConnection() {
        UserDaoHibernateImpl.closeConnection();
    }
}
