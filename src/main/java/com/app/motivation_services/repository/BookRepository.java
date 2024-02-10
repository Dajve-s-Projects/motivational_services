package com.app.motivation_services.repository;

import com.app.motivation_services.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.isAlreadyRead = true")
    List<Book> getBookThatsCurrentlyBeingRead();

    @Query("SELECT b FROM Book b WHERE b.isAlreadyRead = false")
    List<Book> getAllBooksThatAreAlreadyRead();
}
