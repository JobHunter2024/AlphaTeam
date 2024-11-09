package com.example.task.aspects;

import com.example.task.processor.LoggingAPIClient;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final LoggingAPIClient loggingAPIClient;

    @Autowired
    public LoggingAspect(LoggingAPIClient loggingAPIClient) {
        this.loggingAPIClient = loggingAPIClient;
    }

    @AfterReturning(pointcut = "execution(* com.example.task.processor.TaskProcessor.getResponse())", returning = "response")
    public void logFetchingResponse(String response) {
        String message = "Fetching response processed: " + response;
        String status = "SUCCESS";
        loggingAPIClient.sendLog(message, status);
    }
}