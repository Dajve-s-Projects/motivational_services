package com.app.motivation_services.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(schema = "Note", name = "Note")
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NonNull
    private int currentPage;

    @Column
    @NonNull
    private LocalDateTime dateOfNoteSubmission;

    public Note() {}

    public Note(Long id, @NonNull int currentPage) {
        this.id = id;
        this.currentPage = currentPage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
