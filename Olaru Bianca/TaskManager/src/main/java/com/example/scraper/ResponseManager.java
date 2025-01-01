package com.example.scraper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ResponseManager {

    private final ObjectMapper objectMapper;

    public ResponseManager(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String processScrapingResult(ScrapingResult result) {
        if (result == null || !result.success) {
            throw new IllegalArgumentException("Invalid or unsuccessful ScrapingResult");
        }

        //TODO

        String content = result.getData();
        try {
            return objectMapper.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting content to JSON", e);
        }
    }
}