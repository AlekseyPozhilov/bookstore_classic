package com.belhard.bookstore.controller;

import com.belhard.bookstore.PropertiesManager;
import com.belhard.bookstore.controller.create.book.CreateBookController;
import com.belhard.bookstore.controller.create.book.CreateBookFormController;
import com.belhard.bookstore.controller.create.user.CreateUserController;
import com.belhard.bookstore.connection.DataSource;
import com.belhard.bookstore.connection.DataSourceImpl;
import com.belhard.bookstore.controller.book.BookController;
import com.belhard.bookstore.controller.book.BooksController;
import com.belhard.bookstore.controller.create.user.CreateUserFormController;
import com.belhard.bookstore.controller.edit.book.EditBookController;
import com.belhard.bookstore.controller.edit.book.EditBookFormController;
import com.belhard.bookstore.controller.edit.user.EditUserController;
import com.belhard.bookstore.controller.edit.user.EditUserFormController;
import com.belhard.bookstore.controller.error.ErrorController;
import com.belhard.bookstore.controller.home.HomeController;
import com.belhard.bookstore.controller.user.UserController;
import com.belhard.bookstore.controller.user.UsersController;
import com.belhard.bookstore.dao.book.BookDao;
import com.belhard.bookstore.dao.book.BookDaoImpl;
import com.belhard.bookstore.dao.user.UserDao;
import com.belhard.bookstore.dao.user.UserDaoImpl;
import com.belhard.bookstore.service.book.BookService;
import com.belhard.bookstore.service.book.BookServiceImpl;
import com.belhard.bookstore.service.user.UserService;
import com.belhard.bookstore.service.user.UserServiceImpl;
import lombok.extern.log4j.Log4j2;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class ControllerFactory implements Closeable {
    private final DataSource dataSource;
    private final Map<String, Controller> map;
    private final List<Closeable> closeables;
    public static final ControllerFactory INSTANCE = new ControllerFactory();

    public ControllerFactory() {
        PropertiesManager propertiesManager = new PropertiesManager("/application.properties");
        String profile = propertiesManager.getKey("my.app.profile");
        String url = propertiesManager.getKey("my.app.db." + profile + ".url");
        String user = propertiesManager.getKey("my.app.db." + profile + ".user");
        String password = propertiesManager.getKey("my.app.db." + profile + ".password");
        String drv = propertiesManager.getKey("my.app.db." + profile + ".drv");
        int poolSize = 20;
        dataSource = new DataSourceImpl(url, user, password, drv, poolSize);
        closeables = new ArrayList<>();
        closeables.add(dataSource);
        UserDao userDao = new UserDaoImpl(dataSource);
        BookDao bookDao = new BookDaoImpl(dataSource);
        UserService userService = new UserServiceImpl(userDao);
        BookService bookService = new BookServiceImpl(bookDao);

        map = new HashMap<>();
        map.put("user", new UserController(userService));
        map.put("users", new UsersController(userService));
        map.put("book", new BookController(bookService));
        map.put("books", new BooksController(bookService));
        map.put("error", new ErrorController());
        map.put("home", new HomeController());
        map.put("create_user_form", new CreateUserFormController());
        map.put("create_user", new CreateUserController(userService));
        map.put("create_book_form", new CreateBookFormController());
        map.put("create_book", new CreateBookController(bookService));
        map.put("edit_user_form", new EditUserFormController(userService));
        map.put("edit_user", new EditUserController(userService));
        map.put("edit_book_form", new EditBookFormController(bookService));
        map.put("edit_book", new EditBookController(bookService));
    }

    public Controller get(String command) {
        Controller controller = map.get(command);

        if (controller == null) {
            return map.get("error");
        }

        return controller;
    }

    @Override
    public void close() {
        for (Closeable closeable : closeables) {
            try {
                closeable.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
