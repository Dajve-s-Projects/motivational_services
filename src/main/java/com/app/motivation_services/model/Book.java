package com.app.motivation_services.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "BOOK")
@Data
public class Book {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @Column(name = "TITLE")
    @NonNull
    private String title;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Getter
    @Setter
    @Column(name = "DESCRIPTION")
    @NonNull
    private String description;

    @Setter
    @Getter
    @Column(name = "TOTALCHAPTERS")
    private int totalChapters;

    @Getter
    @Setter
    @Column(name = "CURRENTCHAPTER")
    private int currentChapter;

    @Getter
    @Setter
    @Column(name = "TOTALPAGES")
    private int totalPages;

    @Getter
    @Setter
    @Column(name = "CURRENTPAGE")
    private int currentPage;

    @Getter
    @Setter
    @Column(name = "RATING")
    private double rating;

    @Getter
    @Setter
    @Column(name = "ISALREADYREAD")
    private boolean isAlreadyRead;

    @Getter
    @Column(name = "STARTREADINGDATE")
    private LocalDateTime startReadingDate;

    @Getter
    @Setter
    @Column(name = "ENDREADINGDATE")
    private LocalDateTime endReadingDate;

    @Column(name = "ISCURRENTLYREADING")
    private boolean isCurrentlyReading;

    @Getter
    @Setter
    @Column(name = "BOOKOPINION")
    private String bookOpinion;

    @Getter
    @Setter
    @Column(name = "SPECIALNOTES")
    private String specialNotes;

    public Book() {}

    public void setIsCurrentlyReading(boolean currentlyReading) {
        this.isCurrentlyReading = currentlyReading;
    }

    public boolean getIsCurrentlyReading() {
        return isCurrentlyReading;
    }
}
