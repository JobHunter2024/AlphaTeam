package com.example.taskmanager.aspects;

import com.example.taskmanager.taskprocessor.LoggingAPIClient;

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

    @AfterReturning(pointcut = "execution(* com.example.taskmanager.taskdispatcher.TaskDispatcher.dispatch(..))", returning = "response")
    public void logFetchingResponse(String response) {
        String message = "Fetching response processed: " + response;
        loggingAPIClient.logTaskResult(message);
    }
}