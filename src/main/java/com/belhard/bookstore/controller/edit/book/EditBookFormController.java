package com.belhard.bookstore.controller.edit.book;

import com.belhard.bookstore.controller.Controller;
import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.service.book.BookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditBookFormController implements Controller {
    private final BookService bookService;
    public String execute(HttpServletRequest req) {
        long id = Long.parseLong(req.getParameter("id"));
        BookDto toEdit = bookService.findById(id);
        req.setAttribute("book", toEdit);
        return "jsp/book/editBookForm.jsp";
    }
}
