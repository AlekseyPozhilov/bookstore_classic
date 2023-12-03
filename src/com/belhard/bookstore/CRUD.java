package com.belhard.bookstore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD {
    private Connection connection;

    public CRUD(Connection connection) {
        this.connection = connection;
    }

    public void create(Book book) {
        String query = "INSERT INTO books (id, author, isbn, numberOfPages, price, yearOfPublishing, title) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, book.getId());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setInt(4, book.getNumberOfPages());
            statement.setDouble(5, book.getPrice());
            statement.setInt(6, book.getYearOfPublishing());
            statement.setString(7, book.getTitle());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book read(int id) {
        String query = "SELECT * FROM books WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractBookFromResultSet(resultSet);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void update(Book book) {
        String query = "UPDATE books SET author = ?, isbn = ?, numberOfPages = ?, price = ?, yearOfPublishing = ?, title = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getIsbn());
            statement.setInt(3, book.getNumberOfPages());
            statement.setDouble(4, book.getPrice());
            statement.setInt(5, book.getYearOfPublishing());
            statement.setString(6, book.getTitle());
            statement.setLong(7, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book delete(int id) {
        String query = "DELETE FROM books WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Book> getAll() {
        String query = "SELECT id, author, isbn, numberOfPages, price, yearOfPublishing, title FROM books";
        List<Book> books = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Book book = extractBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    private Book extractBookFromResultSet(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("id");
            String author = resultSet.getString("author");
            String isbn = resultSet.getString("isbn");
            Integer numberOfPages = resultSet.getInt("numberOfPages");
            Double price = resultSet.getDouble("price");
            Integer yearOfPublishing = resultSet.getInt("yearOfPublishing");
            String title = resultSet.getString("title");

            Book book = new Book();
            book.setId(id);
            book.setAuthor(author);
            book.setIsbn(isbn);
            book.setNumberOfPages(numberOfPages);
            book.setPrice(price);
            book.setYearOfPublishing(yearOfPublishing);
            book.setTitle(title);

            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
