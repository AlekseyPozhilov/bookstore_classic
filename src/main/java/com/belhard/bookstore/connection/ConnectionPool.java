package com.belhard.bookstore.connection;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Log4j2
public class ConnectionPool {
    private final int poolSize;
    private final BlockingDeque<ProxyConnection> freeConnections;
    private final Queue<ProxyConnection> givenConnections;

    public ConnectionPool(String URL, String USR, String PSW, String DRV, int poolSize) {
        this.poolSize = poolSize;
        this.freeConnections = new LinkedBlockingDeque<>(this.poolSize);
        this.givenConnections = new ArrayDeque<>();

        try {
            Class.forName("org.postgresql.Driver");
            log.info("Database driver loaded");
            for (int i = 0; i < this.poolSize; i++) {
                Connection connection = DriverManager.getConnection(URL, USR, PSW);
                freeConnections.offer(new ProxyConnection(connection, this));
                log.info("ConnectionPool create connection");
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e.getMessage(), e);
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }

        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection proxy && givenConnections.remove(connection)) {
            freeConnections.offer(proxy);
        } else {
            log.warn("Returned not proxy connection");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < poolSize; i++) {
            try {
                freeConnections.take().reallyClose();
                log.info("Connection closed");
            } catch (SQLException | InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
