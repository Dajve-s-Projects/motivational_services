package com.app.motivation_services.service;

import com.app.motivation_services.model.Author;
import com.app.motivation_services.model.Book;
import com.app.motivation_services.model.EditBook;
import com.app.motivation_services.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
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

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBookInReading() {
        List<Book> readBook = bookRepository.getBookThatsCurrentlyBeingRead()
                .stream()
                .filter(Book::getIsCurrentlyReading).toList();

        if (readBook.size() > 1)
            throw new Error("There are multiple books that are in the 'currently reading' state");

        return readBook;
    }

    public List<Book> getAllUnreadBooks() {
        return bookRepository.getAllBooksThatAreAlreadyRead();
    }

    public boolean isBookAlreadyRead(Long id) {
        Optional<Book> bookById = getBookById(id);
        return bookById.map(Book::isAlreadyRead).orElse(false);
    }

    public void markBookAsCurrentlyReading(Long id) {
        Book bookById = getBookById(id).get();

        // SET CURRENT READING DATE TO TODAY
        LocalDateTime currentDate = LocalDateTime.now();
        bookRepository.setCurrentReadingTimeToNowTime(id, currentDate);

        // SET END READING DATE TO A FUTURE DAY
        LocalDateTime futureDate = LocalDateTime.of(9999, 12, 31, 1, 9, 24);
        bookRepository.setEndReadingTimeToDefaultTime(id, futureDate);

        bookRepository.updateBookToReadById(bookById.getId());
    }

    public Optional<List<Book>> getCompletedBooks() {
        return bookRepository.completedBooks();
    }

    public String deleteBookById(Long id) {
        Optional<Book> bookById = getBookById(id);
        String returnMessage;
        if (bookById.isEmpty()) returnMessage = "Book " + id + " is not in the Database";
        else {
            bookRepository.deleteById(id);
            returnMessage = "Book successfully deleted";
        }
        return returnMessage;
    }

    public Book mergeEditFormAndBookObject(EditBook editForm, Book book) {
        Author newAuthor = new Author();
        boolean updatedAuthorAlreadyMatchesCurrentAuthor = editForm.getAuthor().equals(book.getAuthor().getAuthorName());
        if (updatedAuthorAlreadyMatchesCurrentAuthor) newAuthor.setId(book.getAuthor().getId());
        else {
            Optional<Author> authorByAuthorName = authorService.getAuthorByAuthorName(editForm.getAuthor());
            if (authorByAuthorName.isEmpty()) return null;
            newAuthor.setId(authorByAuthorName.get().getId());
        }
        newAuthor.setAuthorName(editForm.getAuthor());

        book.setTitle(editForm.getTitle());
        book.setTotalChapters(editForm.getChapters());
        book.setTotalPages(editForm.getPages());
        book.setAuthor(newAuthor);
        book.setStartReadingDate(editForm.getStartReadingDate());

        return book;
    }
}
