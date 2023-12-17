package com.belhard.bookstore.service.book;

import com.belhard.bookstore.dao.book.BookDao;
import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.entity.Book;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookServiceImpl implements BookService {
    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);
    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<BookDto> findAll() {
        try {
            logger.debug("Fetching all books");

            List<Book> bookEntities = bookDao.getAll();
            List<BookDto> dtos = new ArrayList<>();
            for (Book bookEntity : bookEntities) {
                BookDto dto = new BookDto();
                dto.setId(bookEntity.getId());
                dto.setAuthor(bookEntity.getAuthor());
                dto.setIsbn(bookEntity.getIsbn());
                dto.setNumberOfPages(bookEntity.getNumberOfPages());
                dto.setPrice(bookEntity.getPrice());
                dto.setYearOfPublishing(bookEntity.getYearOfPublishing());
                dto.setTitle(bookEntity.getTitle());
                dtos.add(dto);
            }

            logger.debug("All books received");

            return dtos;
        } catch (SQLException e) {
            logger.error("Failed to find books", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookDto findById(Long id) {
        logger.debug("Fetching book by ID: {}", id);

        Book bookEntity = bookDao.findById(id);
        BookDto dto = new BookDto();
        dto.setId(bookEntity.getId());
        dto.setAuthor(bookEntity.getAuthor());
        dto.setIsbn(bookEntity.getIsbn());
        dto.setNumberOfPages(bookEntity.getNumberOfPages());
        dto.setPrice(bookEntity.getPrice());
        dto.setYearOfPublishing(bookEntity.getYearOfPublishing());
        dto.setTitle(bookEntity.getTitle());

        logger.debug("Book received", dto);
        return dto;
    }

    public BookDto findByIsbn(String isbn) {
        try {
            logger.debug("Fetching book by ISBN: {}", isbn);
            Book bookEntity = bookDao.findByIsbn(isbn);
            BookDto dto = new BookDto();
            dto.setId(bookEntity.getId());
            dto.setAuthor(bookEntity.getAuthor());
            dto.setIsbn(bookEntity.getIsbn());
            dto.setNumberOfPages(bookEntity.getNumberOfPages());
            dto.setPrice(bookEntity.getPrice());
            dto.setYearOfPublishing(bookEntity.getYearOfPublishing());
            dto.setTitle(bookEntity.getTitle());

            logger.debug("Book received: {}", dto);

            return dto;
        } catch (SQLException e) {
            logger.error("Failed to find book: {}", isbn, e);
            throw new RuntimeException(e);
        }
    }

    public List<BookDto> findByAuthor(String author) {
        List<BookDto> dtos = new ArrayList<>();
        try {
            logger.debug("Fetching book by author: {}", author);

            List<Book> bookEntities = bookDao.findByAuthor(author);

            for (Book bookEntity : bookEntities) {
                BookDto dto = new BookDto();
                dto.setId(bookEntity.getId());
                dto.setAuthor(bookEntity.getAuthor());
                dto.setIsbn(bookEntity.getIsbn());
                dto.setNumberOfPages(bookEntity.getNumberOfPages());
                dto.setPrice(bookEntity.getPrice());
                dto.setYearOfPublishing(bookEntity.getYearOfPublishing());
                dto.setTitle(bookEntity.getTitle());
                dtos.add(dto);
            }

            logger.debug("Book received");

            return dtos;
        } catch (SQLException e) {
            logger.error("Failed to find book: {}", author, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookDto create(BookDto dto) {
        try {
            logger.debug("Creating book: {}", dto);
            Book bookEntity = new Book();
            bookEntity.setAuthor(dto.getAuthor());
            bookEntity.setIsbn(dto.getIsbn());
            bookEntity.setNumberOfPages(dto.getNumberOfPages());
            bookEntity.setPrice(dto.getPrice());
            bookEntity.setYearOfPublishing(dto.getYearOfPublishing());
            bookEntity.setTitle(dto.getTitle());

            bookDao.create(bookEntity);

            logger.debug("book created: {}", bookEntity);

            return dto;
        } catch (SQLException e) {
            logger.error("Failed to create book: {}", dto, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookDto update(BookDto dto) {
        try {
            logger.debug("Updating book: {}", dto);
            Book bookEntity = new Book();
            bookEntity.setId(dto.getId());
            bookEntity.setAuthor(dto.getAuthor());
            bookEntity.setIsbn(dto.getIsbn());
            bookEntity.setNumberOfPages(dto.getNumberOfPages());
            bookEntity.setPrice(dto.getPrice());
            bookEntity.setYearOfPublishing(dto.getYearOfPublishing());
            bookEntity.setTitle(dto.getTitle());

            bookDao.update(bookEntity);

            logger.debug("Book updated: {}", bookEntity);

            return dto;
        } catch (SQLException e) {
            logger.error("Failed to update book: {}", dto, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            logger.debug("Deleting book: {}", id);

            bookDao.delete(Math.toIntExact(id));

            logger.debug("Book deleted: {}", id);
        } catch (SQLException e) {
            logger.error("Failed to delete book: {}", id, e);
            throw new RuntimeException(e);
        }
    }
}
