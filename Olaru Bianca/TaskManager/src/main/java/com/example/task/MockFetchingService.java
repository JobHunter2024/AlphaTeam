package com.example.task;

import com.example.task.factory.ServiceRequest;

public class MockFetchingService {

    public String fetchData(ServiceRequest request) {
        String taskType = request.getTaskType();
        System.out.println("Fetching " + taskType + " task");

        try {
            request.getParameters().forEach((key, value) ->
                    System.out.println("Processing element: " + key + " at path: " + value)
            );
            return "Task processed successfully: " + taskType;
        } catch (Exception e) {
            return "Task processing failed. " + e.getMessage();
        }
    }

    public String getResponse() {
        return "Task processed successfully";
    }
}