package com.belhard.bookstore.controller.book;


import com.belhard.bookstore.connection.DataSourceImpl;
import com.belhard.bookstore.dao.book.BookDaoImpl;
import com.belhard.bookstore.dto.book.BookDto;
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
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@WebServlet("/books")
public class BooksController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/bookstore_pozhilov";
    private static final String USR = "postgres";
    private static final String PSW = "root";
    private static final String DRV = "org.postgresql.Driver";
    private BookService bookService = new BookServiceImpl(new BookDaoImpl(new DataSourceImpl(URL, USR, PSW, DRV)));
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (bookService != null) {
                List<BookDto> books = bookService.findAll();
                request.setAttribute("books", books);
                request.getRequestDispatcher("jsp/books.jsp").forward(request, response);
            } else {
                logger.error("BookService is null");
                throw new RuntimeException("BookService is not available");
            }
        } catch (ServletException e) {
            logger.error("Failed {}", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Failed {}", e);
            throw new RuntimeException(e);
        }
    }
}
