package com.belhard.bookstore.controller.book;

import com.belhard.bookstore.controller.Controller;
import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.service.book.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class BooksController implements Controller {
    private BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (bookService != null) {
            List<BookDto> books = bookService.findAll();
            request.setAttribute("books", books);
            return "jsp/book/books.jsp";
        } else {
            log.error("BookService is null");
            throw new RuntimeException("BookService is not available");
        }
    }
}
