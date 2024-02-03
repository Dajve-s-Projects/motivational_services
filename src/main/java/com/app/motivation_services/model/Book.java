package com.app.motivation_services.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(schema = "Book", name = "Book")
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
            Long id,
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
        this.id = id;
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
}
