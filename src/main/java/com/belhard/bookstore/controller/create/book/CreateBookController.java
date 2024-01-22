package com.belhard.bookstore.controller.create.book;

import com.belhard.bookstore.controller.Controller;
import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.dto.book.CreateBookDto;
import com.belhard.bookstore.service.book.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class CreateBookController implements Controller {
    private final BookService bookService;
    public String execute(HttpServletRequest req) {
        CreateBookDto toSave = processRequest(req);
        BookDto saved = bookService.create(toSave);
        setRequestAttributes(req, saved);

        return "jsp/book/book.jsp";
    }

    private static void setRequestAttributes(HttpServletRequest req, BookDto saved) {
        req.setAttribute("book", saved);
    }

    private static CreateBookDto processRequest(HttpServletRequest req) {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String isbn = req.getParameter("isbn");
        String priceString = req.getParameter("price");
        BigDecimal price = new BigDecimal(priceString);
        Integer numberOfPages = Integer.valueOf(req.getParameter("numberOfPages"));
        Integer yearOfPublishing = Integer.valueOf(req.getParameter("yearOfPublishing"));

        CreateBookDto dto = new CreateBookDto();
        dto.setTitle(title);
        dto.setAuthor(author);
        dto.setIsbn(isbn);
        dto.setPrice(price);
        dto.setNumberOfPages(numberOfPages);
        dto.setYearOfPublishing(yearOfPublishing);

        return dto;
    }
}
