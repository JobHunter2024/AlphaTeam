package com.example.task.processor;

import java.util.Map;
import java.util.UUID;

public class TaskConfig {
    private final UUID id;
    private String sourceURL;
    private String status;
    private String type;
    private String jsoupPath;

    public TaskConfig(UUID id, String sourceURL, String status, String type, String jsoupPath) {
        this.id = id;
        this.sourceURL = sourceURL;
        this.status = status;
        this.type = type;
        this.jsoupPath = jsoupPath;
    }

    public UUID getTaskId() {
        return id;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJsoupPath() {
        return jsoupPath;
    }

    public void setJsoupPath(String jsoupPath) {
        this.jsoupPath = jsoupPath;
    }

    @Override
    public String toString() {
        return "TaskConfig{" +
                "id=" + id +
                ", sourceURL='" + sourceURL + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", jsoupPath=" + jsoupPath +
                '}';
    }
}