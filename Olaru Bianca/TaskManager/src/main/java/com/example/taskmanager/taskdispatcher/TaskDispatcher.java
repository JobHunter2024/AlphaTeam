package com.example.taskmanager.taskdispatcher;

import com.example.taskmanager.taskfactory.TaskQueueManager;
import com.example.taskmanager.taskprocessor.TaskProcessor;

public class TaskDispatcher {
    private TaskQueueManager taskQueueManager;
    private FetchingService fetchingService; // will be implemented in different component
    private TaskProcessor taskProcessor;

    public TaskDispatcher(TaskQueueManager taskQueueManager, FetchingService fetchingService, TaskProcessor taskProcessor) {
        this.taskQueueManager = taskQueueManager;
        this.fetchingService = fetchingService;
        this.taskProcessor = taskProcessor;
    }

    public void dispatch() {
    }
}
