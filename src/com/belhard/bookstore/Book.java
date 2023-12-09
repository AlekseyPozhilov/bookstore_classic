package com.belhard.bookstore;

import java.math.BigDecimal;
import java.util.Objects;

public class Book {
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(author, book.author) && Objects.equals(isbn, book.isbn) && Objects.equals(numberOfPages, book.numberOfPages) && Objects.equals(price, book.price) && Objects.equals(yearOfPublishing, book.yearOfPublishing) && Objects.equals(title, book.title);
    }

    public int hashCode() {
        return Objects.hash(id, author, isbn, numberOfPages, price, yearOfPublishing, title);
    }

    public String toString() {
        return "Book{" +
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
