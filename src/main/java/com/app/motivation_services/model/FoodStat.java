package com.app.motivation_services.model;

import com.app.motivation_services.emums.FoodType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(schema = "FOODSTAT", name = "FOODSTAT")
@Data
public class FoodStat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NonNull
    private FoodType dailyStatus;

    @Column
    @NonNull
    private LocalDateTime dateSubmitted;

    public FoodStat() {
    }

    public FoodStat(Long id, @NonNull FoodType dailyStatus, @NonNull LocalDateTime dateSubmitted) {
        this.id = id;
        this.dailyStatus = dailyStatus;
        this.dateSubmitted = dateSubmitted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
