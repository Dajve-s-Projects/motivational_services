package com.app.motivation_services.controller;

import com.app.motivation_services.model.Author;
import com.app.motivation_services.model.Book;
import com.app.motivation_services.model.EditBook;
import com.app.motivation_services.model.SubmitBook;
import com.app.motivation_services.service.AuthorService;
import com.app.motivation_services.service.BookService;
import jdk.jfr.Description;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/book/filter")
    @Description("Returns all books that have NOT been read or return a SINGLE" +
            "book that is currently being read depending on the 'read' parameter")
    public ResponseEntity<List<Book>> getReadBooks(@RequestParam(required = false) Boolean read) {
        try {
            if (read == null) {
                List<Book> booksList = new ArrayList<>(bookService.getAllBooks());

                if (booksList.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(booksList, HttpStatus.OK);
            } else if (read) {
                List<Book> bookCurrentlyBeingRead = bookService.getBookInReading();
                return new ResponseEntity<>(bookCurrentlyBeingRead, HttpStatus.OK);
            } else {
                List<Book> unreadBooks = bookService.getAllUnreadBooks();
                return new ResponseEntity<>(unreadBooks, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/book/completed")
    @Description("Return all books that have been read")
    public ResponseEntity<List<Book>> getAllCompletedBooks() {
        return new ResponseEntity<>(bookService.getCompletedBooks().get(), HttpStatus.OK);
    }

    @PutMapping("/update/book/{book-id}")
    @Description("Update a book by ID")
    public ResponseEntity<?> updateBookById(@PathVariable("book-id") Long id, @RequestBody Book book) {
        Optional<Book> bookById = bookService.getBookById(id);

        if (bookById.isEmpty()) return new ResponseEntity<>("No book found with id of " + id, HttpStatus.NOT_FOUND);

        Book existingBook = bookById.get();

        Author existingAuthor = existingBook.getAuthor();

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(existingAuthor);
        existingBook.setDescription(book.getDescription());
        existingBook.setTotalChapters(book.getTotalChapters());
        existingBook.setCurrentPage(book.getCurrentPage());
        existingBook.setTotalPages(book.getTotalPages());
        existingBook.setCurrentChapter(book.getCurrentChapter());
        existingBook.setRating(book.getRating());
        existingBook.setAlreadyRead(book.isAlreadyRead());
        existingBook.setIsCurrentlyReading(book.getIsCurrentlyReading());
        existingBook.setStartReadingDate(book.getStartReadingDate());
        existingBook.setEndReadingDate(book.getEndReadingDate());
        existingBook.setBookOpinion(book.getBookOpinion());
        existingBook.setSpecialNotes(book.getSpecialNotes());

        bookService.updateBook(existingBook);

        return new ResponseEntity<>(existingBook, HttpStatus.OK);
    }

    @PutMapping("/edit/book/{book-id}")
    @Description("Update a with the edit form")
    public ResponseEntity<String> updateBookWithEditFormById(@PathVariable("book-id") Long id, @RequestBody EditBook editForm) {
        try {
            Book book = bookService.getBookById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
            Book updatedBook = bookService.mergeEditFormAndBookObject(editForm, book);
            if (updatedBook == null)
                return new ResponseEntity<>("There is no author with name: " + editForm.getAuthor(), HttpStatus.NOT_FOUND);
            bookService.updateBook(updatedBook);
            return new ResponseEntity<>("Book successfully updated", HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while updating the book", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/book/read/{book-id}")
    @Description("Read a book, that has not been read already")
    public ResponseEntity<String> readBookById(@PathVariable("book-id") Long id) {
        Optional<Book> bookById = bookService.getBookById(id);

        if (bookById.isEmpty())
            return new ResponseEntity<>("There is no book with the id: " + id, HttpStatus.NOT_FOUND);

        if (bookService.isBookAlreadyRead(id))
            return new ResponseEntity<>("This book is already read", HttpStatus.CONFLICT);

        bookService.markBookAsCurrentlyReading(id);

        return new ResponseEntity<>("Book is now in reading", HttpStatus.ACCEPTED);
    }

    @PutMapping("/book/submit/{book-id}")
    @Description("Submit (through the submit UI form) a book that is in the process of reading to a book that is already read." +
            "FYI the submissionDate in the SubmitBook object is overridden when submitting the book. A recommended default for the request paylaod is 9999-12-31 01:09:24")
    public ResponseEntity<String> submitBook(@PathVariable("book-id") Long id, @RequestBody @NonNull SubmitBook submitBook) {
        String submitBookResponse = bookService.submitBook(id, submitBook);
        if (submitBookResponse.contains("submitted")) return new ResponseEntity<>(submitBookResponse, HttpStatus.OK);
        return new ResponseEntity<>(submitBookResponse, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/book")
    @Description("Add a book")
    public ResponseEntity<String> addBook(@RequestBody @NonNull Book newBook) {
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
        book.setIsCurrentlyReading(newBook.getIsCurrentlyReading());
        book.setStartReadingDate(newBook.getStartReadingDate());
        book.setEndReadingDate(newBook.getEndReadingDate());
        book.setBookOpinion(newBook.getBookOpinion());
        book.setSpecialNotes(newBook.getSpecialNotes());

        bookService.createBook(book);

        return new ResponseEntity<>("Book successfully made", HttpStatus.CREATED);
    }

    @DeleteMapping("/book/{book-id}")
    @Description("Delete book by ID")
    public ResponseEntity<String> deleteBookById(@PathVariable("book-id") Long id) {
        String responseMessage = bookService.deleteBookById(id);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK); // this httpstatus is ok regardless of success of deleting book - fix later!!
    }
}
