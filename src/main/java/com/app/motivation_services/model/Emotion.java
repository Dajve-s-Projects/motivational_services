package com.app.motivation_services.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "EMOTION")
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

    public Emotion(@NonNull String emotion) {
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
