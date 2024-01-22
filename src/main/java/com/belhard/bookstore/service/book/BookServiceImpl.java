package com.belhard.bookstore.service.book;

import com.belhard.bookstore.dao.book.BookDao;
import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.dto.book.CreateBookDto;
import com.belhard.bookstore.entity.Book;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<BookDto> findAll() {
        try {
            log.debug("Fetching all books");

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

            log.debug("All books received");

            return dtos;
        } catch (SQLException e) {
            log.error("Failed to find books", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookDto findById(Long id) {
        log.debug("Fetching book by ID: {}", id);

        Book bookEntity = bookDao.findById(id);
        if (bookEntity == null) {
            log.error("Error");
            throw new RuntimeException("No user with id:" + id);
        }
        return bookReadDto(bookEntity);
    }

    private static BookDto bookReadDto(Book bookEntity) {
        BookDto dto = new BookDto();
        dto.setId(bookEntity.getId());
        dto.setAuthor(bookEntity.getAuthor());
        dto.setIsbn(bookEntity.getIsbn());
        dto.setNumberOfPages(bookEntity.getNumberOfPages());
        dto.setPrice(bookEntity.getPrice());
        dto.setYearOfPublishing(bookEntity.getYearOfPublishing());
        dto.setTitle(bookEntity.getTitle());

        return dto;
    }

    public BookDto findByIsbn(String isbn) {
        try {
            log.debug("Fetching book by ISBN: {}", isbn);
            Book bookEntity = bookDao.findByIsbn(isbn);
            BookDto dto = new BookDto();
            dto.setId(bookEntity.getId());
            dto.setAuthor(bookEntity.getAuthor());
            dto.setIsbn(bookEntity.getIsbn());
            dto.setNumberOfPages(bookEntity.getNumberOfPages());
            dto.setPrice(bookEntity.getPrice());
            dto.setYearOfPublishing(bookEntity.getYearOfPublishing());
            dto.setTitle(bookEntity.getTitle());

            log.debug("Book received: {}", dto);

            return dto;
        } catch (SQLException e) {
            log.error("Failed to find book: {}", isbn, e);
            throw new RuntimeException(e);
        }
    }

    public List<BookDto> findByAuthor(String author) {
        List<BookDto> dtos = new ArrayList<>();
        try {
            log.debug("Fetching book by author: {}", author);

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

            log.debug("Book received");

            return dtos;
        } catch (SQLException e) {
            log.error("Failed to find book: {}", author, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookDto create(CreateBookDto dto) {
        try {
            log.debug("Creating book: {}", dto);

            Book book = toEntity(dto);
            Book created = bookDao.create(book);

            log.debug("book created: {}", book);

            return bookReadDto(created);
        } catch (SQLException e) {
            log.error("Failed to create book: {}", dto, e);
            throw new RuntimeException(e);
        }
    }

    private static Book toEntity(CreateBookDto dto) {
        Book bookEntity = new Book();
        bookEntity.setAuthor(dto.getAuthor());
        bookEntity.setIsbn(dto.getIsbn());
        bookEntity.setNumberOfPages(dto.getNumberOfPages());
        bookEntity.setPrice(dto.getPrice());
        bookEntity.setYearOfPublishing(dto.getYearOfPublishing());
        bookEntity.setTitle(dto.getTitle());
        return bookEntity;
    }

    @Override
    public BookDto update(BookDto dto) {
        try {
            log.debug("Updating book: {}", dto);
            Book bookEntity = new Book();
            bookEntity.setId(dto.getId());
            bookEntity.setAuthor(dto.getAuthor());
            bookEntity.setIsbn(dto.getIsbn());
            bookEntity.setNumberOfPages(dto.getNumberOfPages());
            bookEntity.setPrice(dto.getPrice());
            bookEntity.setYearOfPublishing(dto.getYearOfPublishing());
            bookEntity.setTitle(dto.getTitle());

            bookDao.update(bookEntity);

            log.debug("Book updated: {}", bookEntity);

            return dto;
        } catch (SQLException e) {
            log.error("Failed to update book: {}", dto, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            log.debug("Deleting book: {}", id);

            bookDao.delete(Math.toIntExact(id));

            log.debug("Book deleted: {}", id);
        } catch (SQLException e) {
            log.error("Failed to delete book: {}", id, e);
            throw new RuntimeException(e);
        }
    }
}
