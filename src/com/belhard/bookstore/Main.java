package com.belhard.bookstore;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/bookstore_bh";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            consoleApp(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void consoleApp(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("List of commands:\n " +
                "1)all\n " +
                "2)get{id}\n " +
                "3)delete{id}\n " +
                "4)create{id, author, isbn, numberOfPages, price, yearOfPublishing, title}\n " +
                "5)update{author, isbn, numberOfPages, price, yearOfPublishing, title}\n " +
                "6)exit\n Enter your request: ");

        while (true) {
            String command = scanner.nextLine();
            CRUD crud = new CRUD(connection);

            switch (command) {
                case "all":
                    printDataBase(connection);
                    break;
                case "exit":
                    System.exit(0);
                    break;
                case "get":
                    System.out.println("Enter ID book: ");
                    int bookId = scanner.nextInt();
                    Book book = crud.read(bookId);
                    if (book != null) {
                        System.out.println(book.toString());
                    } else {
                        System.out.println("Book with ID " + bookId + " not found.");
                    }
                    break;
                case "delete":
                    System.out.println("Enter ID book: ");
                    int bookIdDel = scanner.nextInt();
                    Book deletedBook = crud.delete(bookIdDel);
                    if (deletedBook != null) {
                        System.out.println(deletedBook.toString());
                        System.out.println("Book deleted!");
                    } else {
                        System.out.println("Book with ID " + bookIdDel + " not found.");
                    }
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }

    private static void printDataBase(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, author, isbn, numberOfPages, price, yearOfPublishing, title FROM books");

            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String author = resultSet.getString(2);
                String isbn = resultSet.getString(3);
                Integer numberOfPages = resultSet.getInt(4);
                Double price = resultSet.getDouble(5);
                Integer yearOfPublishing = resultSet.getInt(6);
                String title = resultSet.getString(7);

                System.out.printf("book {id = %d, author = %s, isbn = %s, numberOfPages = %d, price = %f$, yearOfPublishing = %d, title = %s}%n", id, author, isbn, numberOfPages, price, yearOfPublishing, title);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
