package com.belhard.bookstore.main;

import com.belhard.bookstore.PropertiesManager;
import com.belhard.bookstore.connection.DataSource;
import com.belhard.bookstore.connection.DataSourceImpl;
import com.belhard.bookstore.dao.user.UserDaoImpl;
import com.belhard.bookstore.entity.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainUser {
    public static void main(String[] args) {
        PropertiesManager propertiesManager = new PropertiesManager("application.properties");

        String profile = propertiesManager.getKey("my.app.profile");
        String url = propertiesManager.getKey("my.app.db." + profile + ".url");
        String user = propertiesManager.getKey("my.app.db." + profile + ".user");
        String password = propertiesManager.getKey("my.app.db." + profile + ".password");
        String drv = propertiesManager.getKey("my.app.db." + profile + ".drv");

        int poolSize = 20;

        DataSourceImpl dataSource = new DataSourceImpl(url, user, password, drv, poolSize);

        try (Connection connection = dataSource.getConnection()) {
            consoleApp(dataSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void consoleApp(DataSource dataSource) {
        Scanner scanner = new Scanner(System.in);
        UserDaoImpl crud = new UserDaoImpl(dataSource);

        System.out.println("List of commands:\n " +
                "1)all\n " +
                "2)get{id}\n " +
                "3)delete{id}\n " +
                "4)create{id, firstName, lastName, email, dateOfBirth, gender, numberPhone}\n " +
                "5)update{firstName, lastName, email, dateOfBirth, gender, numberPhone}\n " +
                "6)exit\n Enter your request: ");
        try {
            while (true) {
                String command = scanner.nextLine();

                switch (command) {
                    case "all":
                        crud.getAll();
                        break;
                    case "exit":
                        System.exit(0);
                        break;
                    case "get":
                        System.out.println("Enter ID user: ");
                        int userId = scanner.nextInt();
                        User user = crud.read((long) userId);
                        if (user != null) {
                            System.out.println(user.toString());
                        } else {
                            System.out.println("User with ID " + userId + " not found.");
                        }
                        break;
                    case "delete":
                        System.out.println("Enter ID user: ");
                        int userIdDel = scanner.nextInt();
                        User deletedUser = crud.delete((long) userIdDel);
                        if (deletedUser != null) {
                            System.out.println(deletedUser.toString());
                            System.out.println("Book deleted!");
                        } else {
                            System.out.println("Book with ID " + userIdDel + " not found.");
                        }
                        break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
