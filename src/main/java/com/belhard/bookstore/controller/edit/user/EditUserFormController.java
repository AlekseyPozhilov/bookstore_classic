package com.belhard.bookstore.controller.edit.user;

import com.belhard.bookstore.controller.Controller;
import com.belhard.bookstore.dto.user.UserDto;
import com.belhard.bookstore.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditUserFormController implements Controller {
    private final UserService userService;
    public String execute(HttpServletRequest req) {
        long id = Long.parseLong(req.getParameter("id"));
        UserDto toEdit = userService.findById(id);
        req.setAttribute("user", toEdit);
        return "jsp/user/editUserForm.jsp";
    }
}

