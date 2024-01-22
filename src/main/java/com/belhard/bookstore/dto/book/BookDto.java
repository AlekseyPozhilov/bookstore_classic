package com.belhard.bookstore.dto.book;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {
    private Long id;
    private String author;
    private String isbn;
    private Integer numberOfPages;
    private BigDecimal price;
    private Integer yearOfPublishing;
    private String title;
}
