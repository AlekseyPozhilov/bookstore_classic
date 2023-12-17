package com.belhard.bookstore.dto.book;

import java.math.BigDecimal;
import java.util.Objects;

public class BookDto {
    private Long id;
    private String author;
    private String isbn;
    private Integer numberOfPages;
    private BigDecimal price;
    private Integer yearOfPublishing;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(Integer yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id) && Objects.equals(author, bookDto.author) && Objects.equals(isbn, bookDto.isbn) && Objects.equals(numberOfPages, bookDto.numberOfPages) && Objects.equals(price, bookDto.price) && Objects.equals(yearOfPublishing, bookDto.yearOfPublishing) && Objects.equals(title, bookDto.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, isbn, numberOfPages, price, yearOfPublishing, title);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", price=" + price +
                ", yearOfPublishing=" + yearOfPublishing +
                ", title='" + title + '\'' +
                '}';
    }
}
