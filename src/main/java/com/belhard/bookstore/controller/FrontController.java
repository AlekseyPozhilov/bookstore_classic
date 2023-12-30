package com.belhard.bookstore.controller;

import com.belhard.bookstore.controller.book.BookController;
import com.belhard.bookstore.controller.book.BooksController;
import com.belhard.bookstore.controller.user.UserController;
import com.belhard.bookstore.controller.user.UsersController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/bookstore")
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        switch (command){
            case "user" -> {
                UserController userController = new UserController();
                userController.doGet(req,resp);
            }
            case "users" -> {
                UsersController usersController = new UsersController();
                usersController.doGet(req, resp);
            }
            case "book" -> {
                BookController bookController = new BookController();
                bookController.doGet(req, resp);
            }
            case "books" ->{
                BooksController booksController = new BooksController();
                booksController.doGet(req,resp);
            }
            default -> {
                throw new RuntimeException();
            }
        }
    }
}
