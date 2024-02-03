package com.app.motivation_services.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "BOOK")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NonNull
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column
    @NonNull
    private String description;

    @Column
    @NonNull
    private int totalChapters;

    @Column
    @NonNull
    private int currentChapter;

    @Column
    @NonNull
    private int totalPages;

    @Column
    @NonNull
    private int currentPage;

    @Column
    @NonNull
    private double rating;

    @Column
    @NonNull
    private boolean isAlreadyRead;

    @Column
    @NonNull
    private LocalDateTime startReadingDate;

    @Column
    @NonNull
    private LocalDateTime endReadingDate;

    @Column
    @NonNull
    private String bookOpinion;

    @Column
    @NonNull
    private String specialNotes;

    public Book() {
    }

    public Book(
            @NonNull String title,
            @NonNull Author author,
            @NonNull String description,
            @NonNull int totalChapters,
            @NonNull int currentChapter,
            @NonNull int totalPages,
            @NonNull int currentPage,
            @NonNull double rating,
            @NonNull boolean isAlreadyRead,
            @NonNull LocalDateTime startReadingDate,
            @NonNull LocalDateTime endReadingDate,
            @NonNull String bookOpinion,
            @NonNull String specialNotes
    ) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.totalChapters = totalChapters;
        this.currentChapter = currentChapter;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.rating = rating;
        this.isAlreadyRead = isAlreadyRead;
        this.startReadingDate = startReadingDate;
        this.endReadingDate = endReadingDate;
        this.bookOpinion = bookOpinion;
        this.specialNotes = specialNotes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalChapters() {
        return totalChapters;
    }

    public void setTotalChapters(int totalChapters) {
        this.totalChapters = totalChapters;
    }

    public int getCurrentChapter() {
        return currentChapter;
    }

    public void setCurrentChapter(int currentChapter) {
        this.currentChapter = currentChapter;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isAlreadyRead() {
        return isAlreadyRead;
    }

    public void setAlreadyRead(boolean alreadyRead) {
        isAlreadyRead = alreadyRead;
    }

    public LocalDateTime getStartReadingDate() {
        return startReadingDate;
    }

    public void setStartReadingDate(LocalDateTime startReadingDate) {
        this.startReadingDate = startReadingDate;
    }

    public LocalDateTime getEndReadingDate() {
        return endReadingDate;
    }

    public void setEndReadingDate(LocalDateTime endReadingDate) {
        this.endReadingDate = endReadingDate;
    }

    public String getBookOpinion() {
        return bookOpinion;
    }

    public void setBookOpinion(String bookOpinion) {
        this.bookOpinion = bookOpinion;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public void setSpecialNotes(String specialNotes) {
        this.specialNotes = specialNotes;
    }
}
