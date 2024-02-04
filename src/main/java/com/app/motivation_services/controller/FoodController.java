package com.app.motivation_services.controller;

import com.app.motivation_services.model.FoodStat;
import com.app.motivation_services.service.FoodService;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/food")
    @Description("Get all food stats")
    public ResponseEntity<List<FoodStat>> getAllFoodStats() {
        try {
            List<FoodStat> foodStats = new ArrayList<>(foodService.getAllFoodStats());

            if (foodStats.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(foodStats, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/food")
    @Description("Add a food stat")
    public ResponseEntity<FoodStat> addFoodStatus(@RequestBody FoodStat foodObject) {
        FoodStat foodStat = foodService.addFoodStatus(foodObject);
        return new ResponseEntity<>(foodStat, HttpStatus.CREATED);
    }
}
