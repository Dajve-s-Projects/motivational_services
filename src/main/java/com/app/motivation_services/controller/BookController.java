package com.app.motivation_services.controller;

import com.app.motivation_services.model.Book;
import com.app.motivation_services.service.BookService;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    @Description("Get all books")
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> booksList = new ArrayList<>(bookService.getAllBooks());

            if (booksList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(booksList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{book-id}")
    @Description("Get book by Id")
    public ResponseEntity<Object> getBookById(@PathVariable Long id) {
        Optional<Book> singleBook = bookService.getBookById(id);

        if (singleBook.isEmpty()) {
            return new ResponseEntity<>("Book not found with the id of " + id,
                    HttpStatus.NOT_FOUND);
        }

        Book book = singleBook.get();
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
