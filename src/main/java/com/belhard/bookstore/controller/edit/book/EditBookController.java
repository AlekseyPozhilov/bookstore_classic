package com.belhard.bookstore.controller.edit.book;

import com.belhard.bookstore.controller.Controller;
import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.service.book.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;

@Log4j2
@RequiredArgsConstructor
public class EditBookController implements Controller {
    private final BookService bookService;

    @Override
    public String execute(HttpServletRequest req) {
        BookDto toEdit = processRequest(req);
        BookDto edited = bookService.update(toEdit);
        setRequestAttributes(req, edited);
        return "jsp/book/book.jsp";
    }

    private static void setRequestAttributes(HttpServletRequest req, BookDto edited) {
        req.setAttribute("book", edited);
    }

    private static BookDto processRequest(HttpServletRequest req){
        long id = Long.parseLong(req.getParameter("id"));
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String priceString = req.getParameter("price");
        Integer numberOfPages = Integer.valueOf(req.getParameter("numberOfPages"));
        Integer yearOfPublishing = Integer.valueOf(req.getParameter("yearOfPublishing"));
        BigDecimal price = new BigDecimal(priceString);
        BookDto bookDto = new BookDto();
        bookDto.setId(id);
        bookDto.setTitle(title);
        bookDto.setAuthor(author);
        bookDto.setPrice(price);
        bookDto.setNumberOfPages(numberOfPages);
        bookDto.setYearOfPublishing(yearOfPublishing);
        return bookDto;
    }
}

