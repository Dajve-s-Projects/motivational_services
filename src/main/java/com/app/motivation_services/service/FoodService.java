package com.app.motivation_services.service;

import com.app.motivation_services.model.FoodStat;
import com.app.motivation_services.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public double getKDR() {
        List<FoodStat> allRecords = foodRepository.findAll();
        if (allRecords.isEmpty()) return 0;

        int good = 0;
        int bad = 0;

        for (FoodStat allRecord : allRecords) {
            if (allRecord.getDailyStatus().equalsIgnoreCase("good")) good+=1;
            else bad+=1;
        }

        return (double) good / bad;
    }
}

