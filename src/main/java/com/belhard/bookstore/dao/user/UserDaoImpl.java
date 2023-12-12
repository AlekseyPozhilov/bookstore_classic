package com.belhard.bookstore.dao.user;

import com.belhard.bookstore.connection.DataSource;
import com.belhard.bookstore.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    public static final String INSERT_QUERY = "INSERT INTO users (id, firstName, lastName, email, dateOfBirth, gender, phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String SELECT_QUERY = "SELECT id, firstName, lastName, email, dateOfBirth, gender, phoneNumber FROM users WHERE id = ?";
    public static final String UPDATE_QUERY = "UPDATE users SET firstName = ?, lastName = ?, email = ?, dateOfBirth = ?, gender = ?, phoneNumber = ? WHERE id = ?";
    public static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
    public static final String COUNT_QUERY = "SELECT COUNT(*) FROM users";
    public static final String SELECT_BY_EMAIL_QUERY = "SELECT id, firstName, lastName, email, dateOfBirth, gender, phoneNumber FROM users WHERE email = ?";
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

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(User user) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        String query = "SELECT * FROM users WHERE lastName = ?";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, lastName);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = extractUserFromResultSet(resultSet);
                    userList.add(user);
                }
            }
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

            User user = new User();
            user.setId(id);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setDateOfBirth(dateOfBirth);
            user.setGender(gender);
            user.setPhoneNumber(phoneNumber);

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
