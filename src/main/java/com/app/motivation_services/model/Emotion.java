package com.app.motivation_services.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(schema = "EMOTION", name = "EMOTION")
@Data
public class Emotion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NonNull
    private String emotion;

    public Emotion() {
    }

    public Emotion(Long id, @NonNull String emotion) {
        this.id = id;
        this.emotion = emotion;
    }

    public Long getId() {
        return id;
    }

    @NonNull
    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(@NonNull String emotion) {
        this.emotion = emotion;
    }
}
