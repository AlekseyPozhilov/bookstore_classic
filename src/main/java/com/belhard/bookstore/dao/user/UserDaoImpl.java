package com.belhard.bookstore.dao.user;

import com.belhard.bookstore.connection.DataSource;
import com.belhard.bookstore.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    public static final String INSERT_QUERY = "INSERT INTO users (id, firstName, lastName, email, dateOfBirth, gender, phoneNumber, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SELECT_QUERY = "SELECT id, firstName, lastName, email, dateOfBirth, gender, phoneNumber, password FROM users WHERE id = ?";
    public static final String UPDATE_QUERY = "UPDATE users SET firstName = ?, lastName = ?, email = ?, dateOfBirth = ?, gender = ?, phoneNumber = ?, password = ? WHERE id = ?";
    public static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
    public static final String COUNT_QUERY = "SELECT COUNT(*) FROM users";
    public static final String SELECT_BY_EMAIL_QUERY = "SELECT id, firstName, lastName, email, dateOfBirth, gender, phoneNumber, password FROM users WHERE email = ?";
    public static final String SELECT_BY_LASTNAME_QUERY = "SELECT id, firstName, lastName, email, dateOfBirth, gender, phoneNumber, password FROM users WHERE lastName = ?";
    public static final String SELECT_ALL_QUERY = "SELECT id, firstName, lastName, email, dateOfBirth, gender, phoneNumber, password FROM users";
    private DataSource dataSource;

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(User user) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);

            statement.setLong(1, user.getId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getDateOfBirth());
            statement.setString(6, user.getGender());
            statement.setString(7, user.getPhoneNumber());
            statement.setString(8, user.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User read(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);
                } else {
                    throw new SQLException("User with ID " + id + " not found");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);

            statement.setLong(1, user.getId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getDateOfBirth());
            statement.setString(6, user.getGender());
            statement.setString(7, user.getPhoneNumber());
            statement.setString(8, user.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public User delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public User findByEmail(String email) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_EMAIL_QUERY);
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<User> findByLastName(String lastName) {
        List<User> userList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_LASTNAME_QUERY);
            statement.setString(1, lastName);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = extractUserFromResultSet(resultSet);
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    @Override
    public long countAll() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(COUNT_QUERY);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        }
        return 0;
    }

    private User extractUserFromResultSet(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String email = resultSet.getString("email");
            String dateOfBirth = resultSet.getString("dateOfBirth");
            String gender = resultSet.getString("gender");
            String phoneNumber = resultSet.getString("phoneNumber");
            String password = resultSet.getString("password");

            User user = new User();
            user.setId(id);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setDateOfBirth(dateOfBirth);
            user.setGender(gender);
            user.setPhoneNumber(phoneNumber);
            user.setPassword(password);

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                users.add(user);
                System.out.printf("user {id = %d, firstName = %s, lastName = %s, email = %s, dateOfBirth = %s, gender = %s, phoneNumber = %s, password = %s}%n", user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getDateOfBirth(), user.getGender(), user.getPhoneNumber(), user.getPassword());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
