package com.example.task.processor;

import jakarta.persistence.Column;

import java.util.UUID;

public class TaskConfig {
    private long id;
    private final UUID uuid;
    private String sourceURL;
    private String status;
    private String type;
    private String jsoupPath;

    public String jobUrlPath;
    public String jobDescriptionPath;
    public String jobLocationPath;
    public String jobCompanyPath;
    public String jobTitlePath;
    public String jobDatePath;
    public boolean followLink;


    public TaskConfig(long id, UUID uuid, String sourceURL, String status, String type, String jsoupPath) {
        this.id = id;
        this.uuid = uuid;
        this.sourceURL = sourceURL;
        this.status = status;
        this.type = type;
        this.jsoupPath = jsoupPath;
    }

    public TaskConfig(long id, UUID uuid, String sourceURL, String status, String type, String jobUrlPath, String jobDescriptionPath, String jobLocationPath, String jobCompanyPath, String jobTitlePath ,String jobDatePath, boolean followLink) {
        this.id = id;
        this.uuid = uuid;
        this.sourceURL = sourceURL;
        this.status = status;
        this.type = type;
        this.jobUrlPath = jobUrlPath;
        this.jobDescriptionPath = jobDescriptionPath;
        this.jobLocationPath = jobLocationPath;
        this.jobCompanyPath = jobCompanyPath;
        this.jobTitlePath = jobTitlePath;
        this.jobDatePath = jobDatePath;
        this.followLink = followLink;
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



    //Getters and setters
    public String getJobUrlPath() {
        return jobUrlPath;
    }

    public void setJobUrlPath(String jobUrlPath) {
        this.jobUrlPath = jobUrlPath;
    }

    public String getJobDescriptionPath() {
        return jobDescriptionPath;
    }

    public void setJobDescriptionPath(String jobDescriptionPath) {
        this.jobDescriptionPath = jobDescriptionPath;
    }

    public String getJobLocationPath() {
        return jobLocationPath;
    }

    public void setJobLocationPath(String jobLocationPath) {
        this.jobLocationPath = jobLocationPath;
    }

    public String getJobCompanyPath() {
        return jobCompanyPath;
    }

    public void setJobCompanyPath(String jobCompanyPath) {
        this.jobCompanyPath = jobCompanyPath;
    }

    public String getJobTitlePath() {
        return jobTitlePath;
    }

    public void setJobTitlePath(String jobTitlePath) {
        this.jobTitlePath = jobTitlePath;
    }

    public String getJobDatePath() {
        return jobDatePath;
    }

    public void setJobDatePath(String jobDatePath) {
        this.jobDatePath = jobDatePath;
    }

    public boolean isFollowLink() {
        return followLink;
    }

    public void setFollowLink(boolean followLink) {
        this.followLink = followLink;
    }
}