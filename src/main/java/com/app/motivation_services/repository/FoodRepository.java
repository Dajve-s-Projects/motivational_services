package com.app.motivation_services.repository;

import com.app.motivation_services.model.FoodStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<FoodStat, Long> {
}
