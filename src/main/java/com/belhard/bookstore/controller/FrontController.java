package com.belhard.bookstore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebServlet("/bookstore")
public class FrontController extends HttpServlet {
    private ControllerFactory controllerFactory;

    @Override
    public void init() {
        log.info("Init called");
        controllerFactory = new ControllerFactory();
        log.info("Init completed");
    }

    @Override
    public void destroy() {
        log.info("Destroy called");
        controllerFactory.close();
        log.info("Destroy completed");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page;
        try {
            String command = req.getParameter("command");
            Controller controller = ControllerFactory.INSTANCE.get(command);
            page = controller.execute(req);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            page = ControllerFactory.INSTANCE.get("error").execute(req);
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
