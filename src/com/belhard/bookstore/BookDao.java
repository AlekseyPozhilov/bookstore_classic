package com.belhard.bookstore;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    void create(Book book) throws SQLException;

    Book read(int id) throws SQLException;

    void update(Book book) throws SQLException;

    Book delete(int id) throws SQLException;

    List<Book> getAll() throws SQLException;

}
