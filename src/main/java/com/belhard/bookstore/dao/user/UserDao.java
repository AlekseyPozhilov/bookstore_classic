package com.belhard.bookstore.dao.user;

import com.belhard.bookstore.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User create(User user) throws SQLException;

    User read(Long id) throws SQLException;

    void update(User user) throws SQLException;

    User delete(Long id) throws SQLException;

    User findByEmail(String email) throws SQLException;

    List<User> findByLastName(String lastName) throws SQLException;

    long countAll() throws SQLException;

    List<User> getAll() throws SQLException;
}
