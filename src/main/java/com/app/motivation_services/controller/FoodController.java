package com.app.motivation_services.controller;

import com.app.motivation_services.model.FoodStat;
import com.app.motivation_services.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    // just not working
    @PostMapping("/food")
    public ResponseEntity<FoodStat> addFoodStatus(@RequestBody FoodStat foodObject) {
        FoodStat foodStat = foodService.addFoodStatus(foodObject);
        return new ResponseEntity<>(foodStat, HttpStatus.CREATED);
    }
}
