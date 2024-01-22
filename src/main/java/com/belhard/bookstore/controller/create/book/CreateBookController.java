package com.belhard.bookstore.controller.create.book;

import com.belhard.bookstore.controller.Controller;
import jakarta.servlet.http.HttpServletRequest;

public class CreateBookController implements Controller {
    public String execute(HttpServletRequest req) {
        return "jsp/createBookForm.jsp";
    }
}
