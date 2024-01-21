package com.belhard.bookstore.controller.edit.user;

import com.belhard.bookstore.controller.Controller;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditUserController implements Controller {
    private final UserService userService;

    @Override
    public String execute(HttpServletRequest req) {
        UserDto toEdit = processRequest(req);
        UserDto edited = userService.update(toEdit);
        setRequestAttributes(req, edited);
        return "jsp/user/user.jsp";
    }

    private static void setRequestAttributes(HttpServletRequest req, UserDto edited) {
        req.setAttribute("user", edited);
    }

    private static UserDto processRequest(HttpServletRequest req){
        long id = Long.parseLong(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setFirstName(firstName);
        userDto.setEmail(email);
        userDto.setPassword(password);
        return userDto;
    }
}
