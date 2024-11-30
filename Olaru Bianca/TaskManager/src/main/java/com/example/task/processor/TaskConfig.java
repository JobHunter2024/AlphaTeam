package com.example.task.processor;

import java.util.UUID;

public class TaskConfig {
    private long id;
    private final UUID uuid;
    private String sourceURL;
    private String status;
    private String type;
    private String jsoupPath;

    public TaskConfig(long id, UUID uuid, String sourceURL, String status, String type, String jsoupPath) {
        this.id = id;
        this.uuid = uuid;
        this.sourceURL = sourceURL;
        this.status = status;
        this.type = type;
        this.jsoupPath = jsoupPath;
    }

    public long getId() { return id; }

    public UUID getUUID() {
        return uuid;
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
                "uuid=" + uuid +
                ", sourceURL='" + sourceURL + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", jsoupPath=" + jsoupPath +
                '}';
    }
}