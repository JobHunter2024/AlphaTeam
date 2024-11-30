package com.example.task.factory;

import com.example.task.processor.TaskConfig;

import java.util.UUID;


public class ScrapeTaskCommand implements TaskCommand {
    private final TaskConfig taskConfig;

    public ScrapeTaskCommand(TaskConfig taskConfig) {
        this.taskConfig = taskConfig;
    }

    @Override
    public UUID getTaskId() {
        return taskConfig.getUUID();
    }

    public TaskConfig getConfig() {
        return taskConfig;
    }

}