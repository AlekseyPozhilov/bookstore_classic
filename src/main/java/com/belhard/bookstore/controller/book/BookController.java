package com.belhard.bookstore.controller.book;

import com.belhard.bookstore.dao.book.BookDao;
import com.belhard.bookstore.entity.Book;
import com.belhard.bookstore.service.book.BookService;
import com.belhard.bookstore.service.book.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class BookController {
    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);
    private BookDao bookDao;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String bookId = request.getParameter("id");
            Long id = Long.parseLong(bookId);
            Book book = bookDao.findById(id);
            request.setAttribute("book", book);
            request.getRequestDispatcher("jsp/book.jsp").forward(request, response);

        } catch (ServletException e) {
            logger.error("Failed {}", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Failed {}", e);
            throw new RuntimeException(e);
        }
    }
}
