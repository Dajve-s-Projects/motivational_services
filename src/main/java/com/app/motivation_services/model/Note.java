package com.app.motivation_services.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "Note")
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "note_seq_generator")
    @SequenceGenerator(name = "note_seq_generator", schema = "PUBLIC", sequenceName = "note_seq", allocationSize = 1)
    private Long id;

    @Column
    @NonNull
    private String actualNote;

    @Column
    @NonNull
    private LocalDateTime dateOfNoteSubmission;

    public Note() {
    }

    public Note(Long id, @NonNull String ActualNote) {
        this.id = id;
        this.actualNote = ActualNote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
