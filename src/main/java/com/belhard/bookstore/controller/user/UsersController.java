package com.belhard.bookstore.controller.user;

import com.belhard.bookstore.dao.user.UserDao;
import com.belhard.bookstore.entity.User;
import com.belhard.bookstore.service.book.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UsersController {
    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);
    private UserDao userDao;
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
        List<User> users = userDao.getAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("jsp/users.jsp").forward(request, response);
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
