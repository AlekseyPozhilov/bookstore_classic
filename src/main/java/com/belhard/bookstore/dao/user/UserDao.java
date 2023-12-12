package com.belhard.bookstore.dao.user;

import com.belhard.bookstore.entity.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void create(User user) throws SQLException;

    User read(int id) throws SQLException;

    void update(User user) throws SQLException;

    void delete(User user) throws SQLException;

    User findByEmail(String email) throws SQLException;

    List<User> findByLastName(String lastName) throws SQLException;

    long countAll() throws SQLException;
}
