package com.app.motivation_services.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Note")
@Data
public class Note {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "note_seq_generator")
    @SequenceGenerator(name = "note_seq_generator", schema = "PUBLIC", sequenceName = "note_seq", allocationSize = 1)
    private Long id;

    @Getter
    @Setter
    @NonNull
    @Column(name = "TITLE")
    private String title;

    @Getter
    @Setter
    @NonNull
    @Column(name = "ACTUALNOTE")
    private String actualNote;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "emotion_id", nullable = false)
    private Emotion emotion;

    @Getter
    @Setter
    @NonNull
    @Column(name = "DATEOFNOTESUBMISSION")
    private LocalDateTime dateOfNoteSubmission;

    public Note() {}
}
