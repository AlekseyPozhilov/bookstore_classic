package com.belhard.bookstore.controller.create.book;

import com.belhard.bookstore.controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateBookFormController implements Controller {
    public String execute(HttpServletRequest req) {
        return "jsp/user/createBookForm.jsp";
    }
}
