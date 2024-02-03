package com.app.motivation_services.model;

import jakarta.persistence.*;
import lombok.Data;
import io.micrometer.common.lang.NonNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "FOODSTAT")
@Data
public class FoodStat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DAILYSTATUS")
    @NonNull
    private String dailyStatus;

    @Column(name = "DATESUBMITTED")
    @NonNull
    private LocalDateTime dateSubmitted;

    public FoodStat() {
    }

    public FoodStat(@NonNull String dailyStatus, @NonNull LocalDateTime dateSubmitted) {
        this.dailyStatus = dailyStatus;
        this.dateSubmitted = dateSubmitted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDailyStatus() {
        return dailyStatus;
    }

    public void setDailyStatus(String dailyStatus) {
        this.dailyStatus = dailyStatus;
    }

    public LocalDateTime getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(LocalDateTime dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }
}
