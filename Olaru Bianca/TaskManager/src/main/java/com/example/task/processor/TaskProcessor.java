package com.example.task.processor;

import com.example.task.MockFetchingService;

import java.util.UUID;

public class TaskProcessor {

    private final DatabaseConnector databaseConnector;
    private final ResponseManagerClient responseManagerClient;
    private final MockFetchingService fetchingService;

    public TaskProcessor(DatabaseConnector databaseConnector,
                         ResponseManagerClient responseManagerClient, MockFetchingService fetchingService) {
        this.databaseConnector = databaseConnector;
        this.responseManagerClient = responseManagerClient;
        this.fetchingService = fetchingService;
    }

    public TaskConfig retrieveTaskConfig() {
        UUID uuid = UUID.randomUUID();
        return databaseConnector.fetchTaskConfig(uuid);
    }

    public String getResponse() {
        return fetchingService.getResponse();
    }

//    public boolean sendToResponseManager(String response) {
//        if (response != null && !response.isEmpty()) {
//            return responseManagerClient.getResponse(response);
//        }
//        return false;
//    }
}