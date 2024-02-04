package com.app.motivation_services.model;

import jakarta.persistence.*;
import lombok.Data;
import io.micrometer.common.lang.NonNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "FOODSTAT")
@Data
public class FoodStat {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @Column(name = "DAILYSTATUS")
    @NonNull
    private String dailyStatus;

    @Getter
    @Setter
    @Column(name = "DATESUBMITTED")
    @NonNull
    private LocalDateTime dateSubmitted;

    public FoodStat() {}
}
