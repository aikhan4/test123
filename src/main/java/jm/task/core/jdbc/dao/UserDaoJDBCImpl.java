package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;
    private static final String createUsersQuery = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age TINYINT)";
    private static final String dropUsersQuery = "DROP TABLE IF EXISTS ?";
    private static final String insertUsersQuery = "INSERT INTO ? (name, lastName, age) VALUES (?, ?, ?)";
    private static final String deleteUsersQuery = "DELETE FROM ? WHERE id = ?";
    private static final String selectUsersQuery = "SELECT * FROM ?";
    private static final String cleanUsersQuery = "DELETE FROM ?";

    public UserDaoJDBCImpl (Connection connection) {
        this.connection = connection;
    }
    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createUsersQuery);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }
    public void dropUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(dropUsersQuery)) {
            preparedStatement.setString(1, "users");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertUsersQuery)) {
            preparedStatement.setString(1, "users");
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setByte(4, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }
    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteUsersQuery)) {
            preparedStatement.setString(1, "users");
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectUsersQuery)) {
            preparedStatement.setString(1, "users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4));
                user.setId(resultSet.getLong(1));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
        return userList;
    }
    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(cleanUsersQuery)) {
            preparedStatement.setString(1, "users");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }
    public void closeConnection() {
        try {
            if ((connection != null) && (!connection.isClosed())) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }
}
