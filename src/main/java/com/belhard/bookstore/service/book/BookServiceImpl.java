package com.belhard.bookstore.service.book;

import com.belhard.bookstore.dao.book.BookDao;
import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.entity.Book;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<BookDto> findAll() {
        try {
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
            return dtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookDto findById(Long id) {
        Book bookEntity = bookDao.findById(id);
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
            Book bookEntity = bookDao.findByIsbn(isbn);
            BookDto dto = new BookDto();
            dto.setId(bookEntity.getId());
            dto.setAuthor(bookEntity.getAuthor());
            dto.setIsbn(bookEntity.getIsbn());
            dto.setNumberOfPages(bookEntity.getNumberOfPages());
            dto.setPrice(bookEntity.getPrice());
            dto.setYearOfPublishing(bookEntity.getYearOfPublishing());
            dto.setTitle(bookEntity.getTitle());
            return dto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BookDto> findByAuthor(String author) {
        List<BookDto> dtos = new ArrayList<>();
        try {
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
            return dtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookDto create(BookDto dto) {
        try {
            Book bookEntity = new Book();
            bookEntity.setAuthor(dto.getAuthor());
            bookEntity.setIsbn(dto.getIsbn());
            bookEntity.setNumberOfPages(dto.getNumberOfPages());
            bookEntity.setPrice(dto.getPrice());
            bookEntity.setYearOfPublishing(dto.getYearOfPublishing());
            bookEntity.setTitle(dto.getTitle());

            bookDao.create(bookEntity);

            return dto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookDto update(BookDto dto) {
        try {
            Book bookEntity = new Book();
            bookEntity.setId(dto.getId());
            bookEntity.setAuthor(dto.getAuthor());
            bookEntity.setIsbn(dto.getIsbn());
            bookEntity.setNumberOfPages(dto.getNumberOfPages());
            bookEntity.setPrice(dto.getPrice());
            bookEntity.setYearOfPublishing(dto.getYearOfPublishing());
            bookEntity.setTitle(dto.getTitle());

            bookDao.update(bookEntity);

            return dto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            bookDao.delete(Math.toIntExact(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
