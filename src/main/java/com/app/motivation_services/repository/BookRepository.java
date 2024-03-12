package com.app.motivation_services.repository;

import com.app.motivation_services.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.isCurrentlyReading = true")
    List<Book> getBookThatsCurrentlyBeingRead();

    @Query("SELECT b FROM Book b WHERE b.isAlreadyRead = false")
    List<Book> getAllBooksThatAreAlreadyRead();

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.isCurrentlyReading = TRUE WHERE b.id = :id")
    void updateBookToReadById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.endReadingDate = :futureDate WHERE b.id = :id")
    void setEndReadingTimeToDefaultTime(@Param("id") Long id, @Param("futureDate") LocalDateTime futureDate);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.startReadingDate = :futureDate WHERE b.id = :id")
    void setCurrentReadingTimeToNowTime(@Param("id") Long id, @Param("futureDate") LocalDateTime futureDate);

    @Query("SELECT b FROM Book b WHERE b.isAlreadyRead = true")
    Optional<List<Book>> completedBooks();
}