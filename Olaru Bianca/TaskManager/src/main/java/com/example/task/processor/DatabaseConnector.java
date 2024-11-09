package com.example.taskmanager.taskprocessor;

import java.sql.Connection;

public class DatabaseConnector {
    private Connection connection;

    public DatabaseConnector(Connection connection) {
        this.connection = connection;
    }

    public TaskConfig fetchTaskConfig() {
        return null;
    }

    public void updateTaskStatus(TaskConfig taskConfig, String status) {}
}
