package com.belhard.bookstore.connection;

import java.io.Closeable;
import java.sql.Connection;

public interface DataSource extends Closeable {
    Connection getConnection();

    void close();
}
