package com.app.motivation_services.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class SubmitBook {
    private final String bookOpinion;
    private final String specialNotes;
    private final double rating;
    private final LocalDateTime submissionDate;

    public SubmitBook(String bookOpinion, String specialNotes, double rating, LocalDateTime submissionDate) {
        this.bookOpinion = bookOpinion;
        this.specialNotes = specialNotes;
        this.rating = rating;
        this.submissionDate = submissionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubmitBook that)) return false;
        return Double.compare(rating, that.rating) == 0 && Objects.equals(bookOpinion, that.bookOpinion) && Objects.equals(specialNotes, that.specialNotes) && Objects.equals(submissionDate, that.submissionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookOpinion, specialNotes, rating, submissionDate);
    }
}
