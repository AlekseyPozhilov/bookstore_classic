package com.belhard.bookstore.controller;

import com.belhard.bookstore.controller.ControllerFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebServlet;
import lombok.extern.log4j.Log4j2;

@WebServlet
@Log4j2
public class AppListener implements ServletContextListener {
@Override
    public void contextInitialized(ServletContextEvent sce){
        log.info("Context init event");
        ControllerFactory controllerFactory = ControllerFactory.INSTANCE;
        log.info("{} created", controllerFactory.getClass());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce){
    log.info("Context destroy event");
    ControllerFactory.INSTANCE.close();
    }
}
