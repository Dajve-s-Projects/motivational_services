package com.app.motivation_services.controller;

import com.app.motivation_services.model.Author;
import com.app.motivation_services.model.Book;
import com.app.motivation_services.service.AuthorService;
import com.app.motivation_services.service.BookService;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
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

    @GetMapping("/book/{book-id}")
    @Description("Get book by Id")
    public ResponseEntity<Object> getBookById(@PathVariable("book-id") Long id) {
        Optional<Book> singleBook = bookService.getBookById(id);

        if (singleBook.isEmpty()) {
            return new ResponseEntity<>("Book not found with the id of " + id,
                    HttpStatus.NOT_FOUND);
        }

        Book book = singleBook.get();
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/book")
    @Description("Add a book")
    public ResponseEntity<String> addBook(@RequestBody Book newBook) {
        Optional<Author> authorByAuthorName = authorService.getAuthorByAuthorName(newBook.getAuthor().getAuthorName());

        if (authorByAuthorName.isEmpty()) {
            return new ResponseEntity<>("There is no author named " + newBook.getAuthor().getAuthorName(),
                    HttpStatus.NOT_FOUND);
        }

        Author author = authorByAuthorName.get();
        Book book = new Book();

        book.setTitle(newBook.getTitle());
        book.setAuthor(author);
        book.setDescription(newBook.getDescription());
        book.setTotalChapters(newBook.getTotalChapters());
        book.setCurrentChapter(newBook.getCurrentChapter());
        book.setTotalPages(newBook.getTotalPages());
        book.setCurrentPage(newBook.getCurrentPage());
        book.setRating(newBook.getRating());
        book.setAlreadyRead(newBook.isAlreadyRead());
        book.setStartReadingDate(newBook.getStartReadingDate());
        book.setEndReadingDate(newBook.getEndReadingDate());
        book.setBookOpinion(newBook.getBookOpinion());
        book.setSpecialNotes(newBook.getSpecialNotes());

        bookService.createBook(book);

        return new ResponseEntity<>("Book successfully made", HttpStatus.CREATED);
    }
}
