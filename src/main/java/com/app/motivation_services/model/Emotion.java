package com.app.motivation_services.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EMOTION")
@Data
public class Emotion {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @NonNull
    @Column(name = "EMOTION")
    private String emotion;

    public Emotion() {}
}
