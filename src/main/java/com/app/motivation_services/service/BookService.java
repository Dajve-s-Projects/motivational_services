package com.app.motivation_services.service;

import com.app.motivation_services.model.Book;
import com.app.motivation_services.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getBookInReading() {
        List<Book> readBook = bookRepository.getBookThatsCurrentlyBeingRead()
                .stream()
                .filter(Book -> Book.isAlreadyRead()).toList();

        if (readBook.size() > 1)
            throw new Error("There are multiple books that are in the 'currently reading' state");

        return readBook;
    }

    public List<Book> getAllUnreadBooks() {
        return bookRepository.getAllBooksThatAreAlreadyRead();
    }
}
