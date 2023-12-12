package com.belhard.bookstore.dao.book;

import com.belhard.bookstore.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    void create(Book book) throws SQLException;

    Book read(int id) throws SQLException;

    void update(Book book) throws SQLException;

    Book delete(int id) throws SQLException;

    List<Book> getAll() throws SQLException;

    Book findById(Long id);

    Book findByIsbn(String isbn) throws SQLException;

    List<Book> findByAuthor(String author) throws SQLException;

    long countAll() throws SQLException;
}
