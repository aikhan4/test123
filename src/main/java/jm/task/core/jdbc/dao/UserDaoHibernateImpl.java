package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl {
    private final Session session;
    private static final String createUsersQuery = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age SMALLINT)";
    private static final String dropUsersQuery = "DROP TABLE IF EXISTS users";
    private static final String selectUsersQuery = "FROM User";
    private static final String cleanUsersQuery = "DELETE FROM User";


    public UserDaoHibernateImpl() {
        this.session = new Util().getSession();
    }


    public void createUsersTable() {
        try {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(createUsersQuery).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        try {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(dropUsersQuery).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try {
            Transaction transaction = session.beginTransaction();
            User userToDelete = session.get(User.class, id);
            if (userToDelete != null) {
                session.delete(userToDelete);
            }
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Transaction transaction = session.beginTransaction();
            List<User> usersList = session.createQuery(selectUsersQuery, User.class).list();
            transaction.commit();
            return usersList;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }

    public void cleanUsersTable() {
        try {
            Transaction transaction = session.beginTransaction();
            session.createQuery(cleanUsersQuery).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }

    public void closeConnection() {
        session.close();
    }
}
