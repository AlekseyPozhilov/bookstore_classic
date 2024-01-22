package com.belhard.bookstore.dao.book;

import com.belhard.bookstore.connection.DataSource;
import com.belhard.bookstore.entity.Book;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class BookDaoImpl implements BookDao {
    public static final String SELECT_ISBN_QUERY = "SELECT id, author, numberOfPages, price, yearOfPublishing, title FROM books";
    public static final String SELECT_ID_QUERY = "SELECT id, author, isbn, numberOfPages, price, yearOfPublishing, title FROM books WHERE id = ?";
    private static final String SELECT_AUTHOR_QUERY = "SELECT id, isbn, numberOfPages, price, yearOfPublishing, title FROM books WHERE  author  = ?";
    private static final String INSERT_QUERY = "INSERT INTO books (author, isbn, numberOfPages, price, yearOfPublishing, title) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT id, author, isbn, numberOfPages, price, yearOfPublishing, title FROM books WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE books SET author = ?, isbn = ?, numberOfPages = ?, price = ?, yearOfPublishing = ?, title = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM books WHERE id = ?";
    private static final String SELECTABLE_QUERY = "SELECT id, author, isbn, numberOfPages, price, yearOfPublishing, title FROM books";
    private DataSource dataSource;

    public BookDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void create(Book book) {
        try (Connection connection = dataSource.getConnection()) {
            log.debug("Creating book", book);
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getIsbn());
            statement.setInt(3, book.getNumberOfPages());
            statement.setBigDecimal(4, book.getPrice());
            statement.setInt(5, book.getYearOfPublishing());
            statement.setString(6, book.getTitle());
            statement.executeUpdate();
            log.debug("Book created");
        } catch (SQLException e) {
            log.error("Failed to create book: {}", book, e);
            throw new RuntimeException(e);
        }
    }

    public Book read(int id) {
        try (Connection connection = dataSource.getConnection()) {
            log.debug("Reading book", id);
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractBookFromResultSet(resultSet);
                }
                log.debug("Book has been read");
            } catch (SQLException e) {
                log.error("Failed to read book: {}", id, e);
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            log.error("Failed to read book: {}", id, e);
            throw new RuntimeException(e);
        }

        return null;
    }

    public void update(Book book) {
        try (Connection connection = dataSource.getConnection()) {
            log.debug("Updating book", book);
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getIsbn());
            statement.setInt(3, book.getNumberOfPages());
            statement.setBigDecimal(4, book.getPrice());
            statement.setInt(5, book.getYearOfPublishing());
            statement.setString(6, book.getTitle());
            statement.setLong(7, book.getId());
            statement.executeUpdate();
            log.debug("Book updated");
        } catch (SQLException e) {
            log.error("Failed to update book: {}", book, e);
            throw new RuntimeException(e);
        }
    }

    public Book delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            log.debug("Deleting book", id);
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
            log.debug("Book deleted");
        } catch (SQLException e) {
            log.error("Failed to delete book: {}", id, e);
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            log.debug("Get all books");
            PreparedStatement statement = connection.prepareStatement(SELECTABLE_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = extractBookFromResultSet(resultSet);
                books.add(book);
                System.out.printf("book {id = %d, author = %s, isbn = %s, numberOfPages = %d, price = %f$, yearOfPublishing = %d, title = %s}%n", book.getId(), book.getAuthor(), book.getIsbn(), book.getNumberOfPages(), book.getPrice(), book.getYearOfPublishing(), book.getTitle());
            }
            log.debug("All books received");
        } catch (SQLException e) {
            log.error("Failed to find books");
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
    @Override
    public Book findById(Long id) {
        try (Connection connection = dataSource.getConnection()){
            log.debug("Fetching book by ID: {}", id);
            PreparedStatement statement = connection.prepareStatement(SELECT_ID_QUERY);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                Book book = mapRow(resultSet);
                return book;
            }
            log.debug("Book received");
        }catch (SQLException e){
            log.error("Failed to find book: {}", id, e);
            throw new RuntimeException(e);
        }
        return null;
    }
    public Book findByIsbn(String isbn) {
        try (Connection connection = dataSource.getConnection()) {
            log.debug("Fetching book by isbn: {}", isbn);
            PreparedStatement statement = connection.prepareStatement(SELECT_ISBN_QUERY);
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Book book = mapRow(resultSet);
                return book;
            }
            log.debug("Book received");
        } catch (SQLException e) {
            log.error("Failed to find book: {}", isbn, e);
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            log.debug("Fetching book by author: {}", author);
            PreparedStatement statement = connection.prepareStatement(SELECT_AUTHOR_QUERY);
            statement.setString(1, author);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = mapRow(resultSet);
                books.add(book);
            }
            log.debug("Book received");
        } catch (SQLException e) {
            log.error("Failed to find book: {}", author, e);
            throw new RuntimeException(e);
        }
        return books;
    }
    private static Book mapRow(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setAuthor(resultSet.getString("author"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setNumberOfPages(resultSet.getInt("numberOfPages"));
        book.setPrice(resultSet.getBigDecimal("price"));
        book.setYearOfPublishing(resultSet.getInt("yearOfPublishing"));
        book.setTitle(resultSet.getString("title"));
        return book;
    }
    public long countAll() {
        return getAll().size();
    }
}
