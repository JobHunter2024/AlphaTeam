package com.example.scraper;


import jakarta.persistence.Column;

import java.util.UUID;

public class ScrapingResult {
    private UUID taskId;
    private String url;
    private String path;
    private String data;
    private String errorMessage;
    public  boolean success;

    public String description;
    public String location;
    public String company;
    public String date;
    public String title;

    public ScrapingResult(UUID taskId, String url, String path, String data, String errorMessage, boolean success) {
        this.taskId = taskId;
        this.url = url;
        this.path = path;
        this.data = data;
        this.errorMessage = errorMessage;
        this.success = success;
    }

    public ScrapingResult(UUID taskId, String url, String path, String errorMessage, boolean success, String description, String location, String company, String title, String date) {
        this.taskId = taskId;
        this.url = url;
        this.path = path;
        this.errorMessage = errorMessage;
        this.success = success;
        this.description = description;
        this.location = location;
        this.company = company;
        this.title = title;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return success
                ? "ScrapingResult{taskId=" + taskId + ", url='" + url + "', path='" + path + "', data='" + data + "'}"
                : "ScrapingResult{taskId=" + taskId + ", url='" + url + "', path='" + path + "', error='" + errorMessage + "'}";
    }
}