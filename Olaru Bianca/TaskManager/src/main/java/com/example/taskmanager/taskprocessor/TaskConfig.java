package com.example.taskmanager.taskprocessor;

public class TaskConfig {
    private int id;
    private String sourceURL;
    private String config;
    private String status;

    public TaskConfig(int id, String sourceURL, String config, String status) {
        this.id = id;
        this.sourceURL = sourceURL;
        this.config = config;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public String getConfig() {
        return config;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
