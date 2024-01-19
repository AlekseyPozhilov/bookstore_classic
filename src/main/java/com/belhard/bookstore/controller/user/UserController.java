package com.belhard.bookstore.controller.user;

import com.belhard.bookstore.controller.Controller;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RequiredArgsConstructor
public class UserController implements Controller {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (userService != null) {
            String userId = request.getParameter("id");
            Long id = Long.parseLong(userId);
            UserDto user = userService.findById(id);
            request.setAttribute("user", user);
            return "jsp/user/user.jsp";
        } else {
            log.error("UserService is null");
            throw new RuntimeException("UserService is not available");
        }

    }
}
