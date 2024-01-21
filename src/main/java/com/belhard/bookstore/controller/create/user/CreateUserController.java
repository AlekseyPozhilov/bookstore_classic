package com.belhard.bookstore.controller.create.user;

import com.belhard.bookstore.controller.Controller;
import com.belhard.bookstore.dto.user.CreateUserDto;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class CreateUserController implements Controller {
    private final UserService userService;
    public String execute(HttpServletRequest req) {
        CreateUserDto toSave = processRequest(req);
        UserDto saved = userService.create(toSave);
        setRequestAttributes(req, saved);

        return "jsp/user/user.jsp";
    }

    private static void setRequestAttributes(HttpServletRequest req, UserDto saved) {
        req.setAttribute("user", saved);
    }

    private static CreateUserDto processRequest(HttpServletRequest req) {
        String firstName = req.getParameter("firstName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        CreateUserDto dto = new CreateUserDto();
        dto.setFirstName(firstName);
        dto.setEmail(email);
        dto.setPassword(password);

        return dto;
    }

}
