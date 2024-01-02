package com.belhard.bookstore.controller.error;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class ErrorController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); //Code error 500
            req.getRequestDispatcher("jsp/error.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            log.error("Failed to process request: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
