package com.belhard.bookstore.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Book {
    private Long id;
    private String author;
    private String isbn;
    private Integer numberOfPages;
    private BigDecimal price;
    private Integer yearOfPublishing;
    private String title;
}
