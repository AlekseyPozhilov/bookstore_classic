package com.belhard.bookstore.controller.user;

import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.entity.User;
import com.belhard.bookstore.service.book.BookServiceImpl;
import com.belhard.bookstore.service.user.UserService;
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
@WebServlet("/users")
public class UsersController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);
    private UserService userService;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
        List<UserDto> users = userService.findAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("jsp/users.jsp").forward(request, response);
        } catch (ServletException e) {
            logger.error("Failed {}", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Failed {}", e);
            throw new RuntimeException(e);
        }
    }
}
