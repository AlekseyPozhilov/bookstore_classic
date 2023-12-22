package com.belhard.bookstore.controller.user;

import com.belhard.bookstore.dao.user.UserDao;
import com.belhard.bookstore.entity.User;
import com.belhard.bookstore.service.book.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class UserController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);
    private UserDao userDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userId = request.getParameter("id");
            Long id = Long.parseLong(userId);
            User user = userDao.read(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("jsp/user.jsp").forward(request, response);

        } catch (ServletException e) {
            logger.error("Failed {}", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Failed {}", e);
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
