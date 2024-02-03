package com.app.motivation_services.controller;

import com.app.motivation_services.model.Emotion;
import com.app.motivation_services.service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmotionController {

    private final EmotionService emotionService;

    @Autowired
    public EmotionController(EmotionService emotionService) {
        this.emotionService = emotionService;
    }

    @GetMapping("/emotion")
    public ResponseEntity<List<Emotion>> getAllEmotions() {
        try {
            List<Emotion> emotionsList = new ArrayList<>(emotionService.getAllEmotions());

            if (emotionsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(emotionsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("emotion")
//    public Emotion getEmotionByEmotion() {
//
//    }

    @PostMapping("/emotion")
    public ResponseEntity<Emotion> addEmotion(@RequestBody Emotion newEmotion) {
        Emotion addedEmotion = emotionService.addEmotion(newEmotion);
        return new ResponseEntity<>(addedEmotion, HttpStatus.CREATED);
    }
}
