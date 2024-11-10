package com.example.task.factory;

import com.example.task.processor.TaskConfig;
import com.example.task.queue.TaskQueueManager;

public class TaskFactory {
    private final TaskQueueManager taskQueueManager;

    public TaskFactory(TaskQueueManager taskQueueManager) {
        this.taskQueueManager = taskQueueManager;
    }

    public ScrapeTaskCommand createTask(TaskConfig taskConfig) {
        ScrapeTaskCommand taskCommand;

        switch (taskConfig.getType()) {
            case "scrape":
                taskCommand = new ScrapeTaskCommand(taskConfig);
                break;
            default:
                throw new IllegalArgumentException("Task type does not exist: " + taskConfig.getType());
        }

        taskQueueManager.addToQueue(taskCommand);
        return taskCommand;
    }
}
