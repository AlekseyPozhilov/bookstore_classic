package com.belhard.bookstore.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceImpl implements DataSource {
    private static final Logger logger = LogManager.getLogger(DataSourceImpl.class);
    private  final String url ;
    private final String user;
    private final String password ;
    public DataSourceImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection(){
        try {
            logger.info("Сonnection to the database is being established...");
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            logger.error("Error with connection");
            throw new RuntimeException(e);
        }
    }
}
