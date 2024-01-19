package com.belhard.bookstore.controller.home;

import com.belhard.bookstore.controller.Controller;
import jakarta.servlet.http.HttpServletRequest;

public class HomeController implements Controller {
    public String execute(HttpServletRequest req) {
        return "jsp/index.jsp";
    }
}
