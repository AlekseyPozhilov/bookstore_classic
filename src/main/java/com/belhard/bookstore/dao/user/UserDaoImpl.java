package com.belhard.bookstore.dao.user;

import com.belhard.bookstore.connection.DataSource;
import com.belhard.bookstore.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(User user) {
        try (Connection connection = dataSource.getConnection()) {
            logger.debug("Creating user", user);

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

            logger.debug("User created");
        } catch (SQLException e) {
            logger.error("Failed to create user: {}", user, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public User read(int id) {
        try (Connection connection = dataSource.getConnection()) {
            logger.debug("Reading user", id);
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);
                }
                logger.debug("User has been read");
            } catch (SQLException e) {
                logger.error("Failed to read user: {}", id, e);
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            logger.error("Failed to read user: {}", id, e);
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(User user) {
        try (Connection connection = dataSource.getConnection()) {
            logger.debug("Updating user", user);
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

            logger.debug("User updated");
        } catch (SQLException e) {
            logger.error("Failed to update user: {}", user, e);
            throw new RuntimeException();
        }
    }

    @Override
    public User delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            logger.debug("Deleting user", id);
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, id);
            statement.executeUpdate();
            logger.debug("User deleted");
        } catch (SQLException e) {
            logger.error("Failed to delete user: {}", id, e);
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public User findByEmail(String email) {
        try (Connection connection = dataSource.getConnection()) {
            logger.debug("Fetching user by email: {}", email);

            PreparedStatement statement = connection.prepareStatement(SELECT_BY_EMAIL_QUERY);
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);
                }
                logger.debug("User received");
            }
        } catch (SQLException e) {
            logger.error("Failed to find user: {}", email, e);
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<User> findByLastName(String lastName) {
        List<User> userList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            logger.debug("Fetching user by lastName: {}", lastName);

            PreparedStatement statement = connection.prepareStatement(SELECT_BY_LASTNAME_QUERY);
            statement.setString(1, lastName);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = extractUserFromResultSet(resultSet);
                    userList.add(user);
                }
                logger.debug("User received");
            }
        } catch (SQLException e) {
            logger.error("Failed to find user: {}", lastName, e);
            throw new RuntimeException(e);
        }

        return userList;
    }

    @Override
    public long countAll() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(COUNT_QUERY);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e){
            logger.error("ERROR");
            throw new RuntimeException(e);
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
            logger.error("ERROR");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            logger.debug("Get all users");
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                users.add(user);
                System.out.printf("user {id = %d, firstName = %s, lastName = %s, email = %s, dateOfBirth = %s, gender = %s, phoneNumber = %s, password = %s}%n", user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getDateOfBirth(), user.getGender(), user.getPhoneNumber(), user.getPassword());
            }
            logger.debug("All users received");
        } catch (SQLException e) {
            logger.error("Failed to find users", e);
            throw new RuntimeException(e);
        }
        return users;
    }
}