package com.example.task.processor;

import org.springframework.stereotype.Component;

@Component
public class LoggingAPIClient {

    private String apiEndpoint;

    public LoggingAPIClient() {
    }

    public void sendLog(String message) {
        System.out.println("Message: " + message);
    }
}