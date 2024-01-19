package com.belhard.bookstore.commands;

import com.belhard.bookstore.controller.Controller;
import jakarta.servlet.http.HttpServletRequest;

public class CreateUserCommand implements Controller {
    public String execute(HttpServletRequest req) {
        return "jsp/user/createUserForm.jsp";
    }

}
