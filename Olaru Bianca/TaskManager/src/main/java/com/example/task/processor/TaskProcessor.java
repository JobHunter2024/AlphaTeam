package com.example.taskmanager.taskprocessor;

//implements facade pattern - acts as interface that helps subsystems
//communicate with other modules, which simplifies interactions

public class TaskProcessor {
    DatabaseConnector databaseConnector;
    LoggingAPIClient loggingAPIClient;
    ResponseManagerClient responseManagerClient;


    public TaskConfig retrieveTask() {
        return null;
    }

    public void processPendingTasks(TaskConfig taskConfig) {}

    public boolean logResult(String response) {
        return false;
    }

    public boolean sendToResponseManager(String response) {
        return false;
    }
}
