package com.example.task.factory;

import java.util.Map;
import java.util.Objects;

public class ServiceRequest {
    private String taskType;
    private Map<String, String> parameters;

    public ServiceRequest(String taskType, Map<String, String> parameters) {
        this.taskType = taskType;
        this.parameters = parameters;
    }

    public String getTaskType() {
        return taskType;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "ServiceRequest{" +
                "taskType='" + taskType + '\'' +
                ", parameters=" + parameters +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceRequest that = (ServiceRequest) o;
        return Objects.equals(taskType, that.taskType) && Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskType, parameters);
    }

}