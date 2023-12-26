package com.belhard.bookstore.controller.book;

import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.entity.Book;
import com.belhard.bookstore.service.book.BookService;
import com.belhard.bookstore.service.book.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
@WebServlet("/book")
public class BookController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);
    private BookService bookService;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String bookId = request.getParameter("id");
            Long id = Long.parseLong(bookId);
            BookDto book = bookService.findById(id);
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
