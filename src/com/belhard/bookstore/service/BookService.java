package com.belhard.bookstore.service;

import com.belhard.bookstore.BookDto;

import java.sql.SQLException;
import java.util.List;
public interface BookService {
    	List<BookDto> findAll();
    	BookDto findById(Long id);
		BookDto findByIsbn(String isbn) throws SQLException;
		List<BookDto> findByAuthor(String author);
    	BookDto create(BookDto dto);
    	BookDto update(BookDto dto);
    	void delete(Long id);
}
