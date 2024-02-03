package com.app.motivation_services.service;

import com.app.motivation_services.model.FoodStat;
import com.app.motivation_services.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public FoodStat addFoodStatus(FoodStat foodStat) {
        return foodRepository.save(foodStat);
    }

    public List<FoodStat> getAllFoodStats() {
        return foodRepository.findAll();
    }
}
