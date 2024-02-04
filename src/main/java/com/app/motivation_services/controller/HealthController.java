package com.app.motivation_services.controller;

import com.app.motivation_services.service.HealthService;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @Autowired
    private HealthService healthService;

    @GetMapping("/health")
    @Description("Check health of the service")
    public ResponseEntity<String> healthCheck() {
        String healthResponse = healthService.healthCheck();
        return ResponseEntity.ok(healthResponse);
    }
}
