package com.example.scraper;


import java.util.UUID;

public class ScrapingResult {
    private final UUID taskId;
    private final String url;
    private final String path;
    private final String data;
    private final String errorMessage;
    public final boolean success;

    public ScrapingResult(UUID taskId, String url, String path, String data, String errorMessage, boolean success) {
        this.taskId = taskId;
        this.url = url;
        this.path = path;
        this.data = data;
        this.errorMessage = errorMessage;
        this.success = success;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }

    public String getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return success
                ? "ScrapingResult{taskId=" + taskId + ", url='" + url + "', path='" + path + "', data='" + data + "'}"
                : "ScrapingResult{taskId=" + taskId + ", url='" + url + "', path='" + path + "', error='" + errorMessage + "'}";
    }
}