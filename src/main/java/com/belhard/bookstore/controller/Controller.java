package com.belhard.bookstore.controller;

import jakarta.servlet.http.HttpServletRequest;

public interface Controller {
    String execute(HttpServletRequest req);
}
