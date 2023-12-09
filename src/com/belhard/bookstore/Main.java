package com.belhard.bookstore;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static DataSource dataSource = new DataSource();
    public static void main(String[] args) {
        try (Connection connection = dataSource.getConnection()) {
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
            BookDaoImplCRUD crud = new BookDaoImplCRUD(dataSource);

            switch (command) {
                case "all":
                    crud.getAll();
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
            }
        }
    }
}
