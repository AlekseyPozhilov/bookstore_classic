package com.belhard.bookstore.controller.create;

import com.belhard.bookstore.controller.Controller;
import jakarta.servlet.http.HttpServletRequest;

public class CreateUserFormController implements Controller {
    public String execute(HttpServletRequest req) {
        return "jsp/user/createUserForm.jsp";
    }

}
