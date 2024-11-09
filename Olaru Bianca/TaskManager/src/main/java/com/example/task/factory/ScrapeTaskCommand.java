package com.example.task.factory;

import com.example.task.processor.TaskConfig;

import java.util.HashMap;
import java.util.Map;

public class ScrapeTaskCommand implements TaskCommand {
    private final TaskConfig taskConfig;

    public ScrapeTaskCommand(TaskConfig taskConfig) {
        this.taskConfig = taskConfig;
    }

    @Override
    public ServiceRequest toServiceRequest() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("url", taskConfig.getSourceURL());
        parameters.putAll(taskConfig.getJsoupPath());

        return new ServiceRequest(taskConfig.getType(), parameters);
    }
}