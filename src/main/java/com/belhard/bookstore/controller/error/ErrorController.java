package com.belhard.bookstore.controller.error;

import com.belhard.bookstore.controller.Controller;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ErrorController implements Controller {
    @Override
    public String execute(HttpServletRequest req) {
        return "jsp/error/error.jsp";
    }
}
