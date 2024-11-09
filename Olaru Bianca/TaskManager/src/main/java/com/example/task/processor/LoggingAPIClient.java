package com.example.task.processor;

import org.springframework.stereotype.Component;

@Component
public class LoggingAPIClient {

    private String apiEndpoint = "https://mockLoggingAPI/";

    public LoggingAPIClient() {
    }

    public void sendLog(String message, String status) {
        System.out.println("Message: " + message);
        System.out.println("Status: " + status);
    }
}