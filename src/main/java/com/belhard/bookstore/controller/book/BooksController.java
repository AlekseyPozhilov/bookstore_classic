package com.belhard.bookstore.controller.book;


import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.entity.Book;
import com.belhard.bookstore.service.book.BookService;
import com.belhard.bookstore.service.book.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class BooksController {
    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);
    private BookService bookService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            List<BookDto> books = bookService.findAll();
            request.setAttribute("books", books);
            request.getRequestDispatcher("jsp/books.jsp").forward(request, response);
        } catch (ServletException e) {
            logger.error("Failed {}", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Failed {}", e);
            throw new RuntimeException(e);
        }
    }
}
