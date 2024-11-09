package com.example.taskmanager.taskfactory;

import com.example.taskmanager.taskprocessor.TaskConfig;

// using a combination of the factory and command design patterns
//to keep a cleaner and more flexible code

public class TaskFactory {

    public TaskCommand createTaskCommand(TaskConfig config) {
        return null;
    }

    public ScrapeTaskCommand createScrapeTaskCommand(TaskConfig config) {
        return null;
    }

    public void sendToQueue(TaskCommand taskCommand, TaskQueueManager queueManager) {}

}
