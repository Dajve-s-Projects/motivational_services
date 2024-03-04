package com.app.motivation_services.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class EditBook {
    private final String title;
    private final int chapters;
    private final int pages;
    private final String author;
    private final LocalDateTime startReadingDate;

    public EditBook(String title, int chapters, int pages, String author, LocalDateTime startReadingDate) {
        this.title = title;
        this.chapters = chapters;
        this.pages = pages;
        this.author = author;
        this.startReadingDate = startReadingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EditBook editBook)) return false;
        return chapters == editBook.chapters && pages == editBook.pages && Objects.equals(title, editBook.title) && Objects.equals(author, editBook.author) && Objects.equals(startReadingDate, editBook.startReadingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, chapters, pages, author, startReadingDate);
    }

    @Override
    public String toString() {
        return "EditBook{" +
                "title='" + title + '\'' +
                ", chapters=" + chapters +
                ", pages=" + pages +
                ", author='" + author + '\'' +
                ", startReadingDate=" + startReadingDate +
                '}';
    }
}
