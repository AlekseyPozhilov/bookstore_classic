package com.belhard.bookstore.connection;

import lombok.extern.log4j.Log4j2;


import java.sql.Connection;

@Log4j2
public class DataSourceImpl implements DataSource {
    private final String url;
    private final String user;
    private final String password;
    private final String drv;
    private int pooolSize;
    private ConnectionPool connectionPool;

    public DataSourceImpl(String url, String user, String password, String drv, int poolSize) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.drv = drv;
        connectionPool = new ConnectionPool(url, user, password, drv, poolSize);
    }

    public Connection getConnection() {
        try {
            log.info("Connection to the database is being established...");
            Class.forName("org.postgresql.Driver");
            if (connectionPool == null){
                connectionPool = new ConnectionPool(url, user, password, drv, pooolSize);
            }
            return connectionPool.getConnection();
        }  catch (ClassNotFoundException e) {
            log.error("Error with connection");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        if (connectionPool != null) {
            connectionPool.destroyPool();
            connectionPool = null;
        }
    }
}
