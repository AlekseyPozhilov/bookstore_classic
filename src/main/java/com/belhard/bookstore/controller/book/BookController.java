package com.belhard.bookstore.controller.book;

import com.belhard.bookstore.controller.Controller;
import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.service.book.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class BookController implements Controller {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) {
            if (bookService != null) {
                String bookId = request.getParameter("id");
                Long id = Long.parseLong(bookId);
                BookDto book = bookService.findById(id);
                request.setAttribute("book", book);
                return "jsp/book/book.jsp";
            }else {
                log.error("BookService is null");
                throw new RuntimeException("BookService is not available");
            }

    }
}
