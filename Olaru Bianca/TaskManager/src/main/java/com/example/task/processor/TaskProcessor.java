package com.example.task.processor;

import com.example.task.MockFetchingService;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class TaskProcessor {

    private final DatabaseConnector databaseConnector;
    private final MockFetchingService fetchingService;

    public TaskProcessor(DatabaseConnector databaseConnector,
                          MockFetchingService fetchingService) {
        this.databaseConnector = databaseConnector;
        this.fetchingService = fetchingService;
    }

    public TaskConfig retrieveTaskConfig() {
        UUID uuid = UUID.randomUUID();
        return databaseConnector.fetchTaskConfig(uuid);
    }

    public String getResponse() {
        return fetchingService.getResponse();
    }

    public boolean processResponse(UUID taskId, String response) {
        if (taskId == null || response == null || response.isEmpty()) {
            System.out.println("Failed to process response: invalid input.");
            return false;
        }

        System.out.println("Processing response for task: " + taskId);
        System.out.println("Response: " + response);

        return true;
    }
}