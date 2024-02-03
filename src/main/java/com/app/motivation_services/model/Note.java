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
    private String title;

    @Column
    @NonNull
    private String actualNote;

    @ManyToOne
    @JoinColumn(name = "emotion_id", nullable = false)
    private Emotion emotion;

    @Column
    @NonNull
    private LocalDateTime dateOfNoteSubmission;

    public Note() {
    }

    public Note(Long id, @NonNull String title, @NonNull String actualNote, Emotion emotion, @NonNull LocalDateTime dateOfNoteSubmission) {
        this.id = id;
        this.title = title;
        this.actualNote = actualNote;
        this.emotion = emotion;
        this.dateOfNoteSubmission = dateOfNoteSubmission;
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

    public String getActualNote() {
        return actualNote;
    }

    public void setActualNote(String actualNote) {
        this.actualNote = actualNote;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public LocalDateTime getDateOfNoteSubmission() {
        return dateOfNoteSubmission;
    }

    public void setDateOfNoteSubmission(LocalDateTime dateOfNoteSubmission) {
        this.dateOfNoteSubmission = dateOfNoteSubmission;
    }
}
