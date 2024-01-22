package com.belhard.bookstore.controller.user;

import com.belhard.bookstore.controller.Controller;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class UsersController implements Controller {
    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (userService != null) {
            List<UserDto> users = userService.findAll();
            request.setAttribute("users", users);
            return "jsp/user/users.jsp";
        } else {
            log.error("UserService is null");
            throw new RuntimeException("UserService is not available");
        }

    }
}
