package com.app.motivation_services.service;

import com.app.motivation_services.model.Emotion;
import com.app.motivation_services.repository.EmotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmotionService {

    private final EmotionRepository emotionRepository;

    @Autowired
    public EmotionService(EmotionRepository emotionRepository) {
        this.emotionRepository = emotionRepository;
    }

    public List<Emotion> getAllEmotions() {
        return emotionRepository.findAll();
    }

    public Emotion addEmotion(Emotion emotion) {
        return emotionRepository.save(emotion);
    }

    public Optional<Emotion> getEmotionByEmotion(Emotion currentEmotion) {
        return emotionRepository.findAll()
                .stream()
                .filter(e -> e.getEmotion().equals(currentEmotion.getEmotion()))
                .findFirst();
    }
}
