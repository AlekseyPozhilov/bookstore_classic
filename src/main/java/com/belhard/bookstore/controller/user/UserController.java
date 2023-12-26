package com.belhard.bookstore.controller.user;

import com.belhard.bookstore.connection.DataSourceImpl;
import com.belhard.bookstore.dao.user.UserDaoImpl;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.service.book.BookServiceImpl;
import com.belhard.bookstore.service.user.UserService;
import com.belhard.bookstore.service.user.UserServiceImpl;
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
@WebServlet("/user")
public class UserController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/bookstore_pozhilov";
    private static final String PSW = "root";
    private static final String USR = "postgres";
    private static final String DRV = "org.postgresql.Driver";
    private UserService userService = new UserServiceImpl(new UserDaoImpl(new DataSourceImpl(URL, PSW, USR)));

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Class.forName(DRV); //???????????????????????????
            if (userService != null) {
                String userId = request.getParameter("id");
                Long id = Long.parseLong(userId);
                UserDto user = userService.findById(id);
                request.setAttribute("user", user);
                request.getRequestDispatcher("jsp/user.jsp").forward(request, response);
            } else {
                logger.error("UserService is null");
                throw new RuntimeException("UserService is not available");
            }
        } catch (NumberFormatException e) {
            logger.error("Invalid book ID format: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (ServletException | IOException e) {
            logger.error("Failed to process request: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
