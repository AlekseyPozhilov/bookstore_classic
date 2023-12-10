package com.belhard.bookstore;

import java.sql.Connection;

public interface DataSource {
    Connection getConnection();
}
