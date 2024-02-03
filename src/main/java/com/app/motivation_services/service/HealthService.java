package com.app.motivation_services.service;

import org.springframework.stereotype.Service;

@Service
public class HealthService {

    public String healthCheck() {
        return "Site is healthy";
    }
}
