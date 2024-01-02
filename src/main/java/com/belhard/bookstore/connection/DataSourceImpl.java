package com.belhard.bookstore.connection;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j2
public class DataSourceImpl implements DataSource {
    private  final String url ;
    private final String user;
    private final String password ;
    private final String drv;
    public DataSourceImpl(String url, String user, String password, String drv) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.drv = drv;
    }
    public Connection getConnection(){
        try {
            log.info("Сonnection to the database is being established...");
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            log.error("Error with connection");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
