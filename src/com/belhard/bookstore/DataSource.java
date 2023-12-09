package com.belhard.bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/bookstore_pozhilov";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
    public Connection getConnection(){
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
