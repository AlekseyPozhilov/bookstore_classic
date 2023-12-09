package com.belhard.bookstore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImplCRUD implements BookDao {
    private static final String INSERT_QUERY = "INSERT INTO books (id, author, isbn, numberOfPages, price, yearOfPublishing, title) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM books WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE books SET author = ?, isbn = ?, numberOfPages = ?, price = ?, yearOfPublishing = ?, title = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM books WHERE id = ?";
    private static final String SELECTABLE_QUERY = "SELECT id, author, isbn, numberOfPages, price, yearOfPublishing, title FROM books";
    private final Connection connection;

    public BookDaoImplCRUD(Connection connection) {
        this.connection = connection;
    }

    public void create(Book book) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setLong(1, book.getId());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setInt(4, book.getNumberOfPages());
            statement.setBigDecimal(5, book.getPrice());
            statement.setInt(6, book.getYearOfPublishing());
            statement.setString(7, book.getTitle());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book read(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
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
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getIsbn());
            statement.setInt(3, book.getNumberOfPages());
            statement.setBigDecimal(4, book.getPrice());
            statement.setInt(5, book.getYearOfPublishing());
            statement.setString(6, book.getTitle());
            statement.setLong(7, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECTABLE_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Book book = extractBookFromResultSet(resultSet);
                books.add(book);
                System.out.printf("book {id = %d, author = %s, isbn = %s, numberOfPages = %d, price = %f$, yearOfPublishing = %d, title = %s}%n", book.getId(), book.getAuthor(), book.getIsbn(), book.getNumberOfPages(), book.getPrice(), book.getYearOfPublishing(), book.getTitle());
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
            BigDecimal price = resultSet.getBigDecimal("price");
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
