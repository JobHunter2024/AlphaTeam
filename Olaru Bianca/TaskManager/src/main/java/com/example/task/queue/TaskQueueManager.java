package com.example.task.queue;

import com.example.task.factory.TaskCommand;

public class TaskQueueManager {
    private final TaskQueue taskQueue;

    public TaskQueueManager(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void addToQueue(TaskCommand taskCommand) {
        taskQueue.enqueue(taskCommand);
        System.out.println("Task added to queue: " + taskCommand);
    }

    public void removeFromQueue() {
        TaskCommand task = taskQueue.dequeue();
        if (task != null) {
            System.out.println("Task removed from queue: " + task);
        } else {
            System.out.println("Queue is empty.");
        }
    }

    public TaskCommand getFromQueue() {
        TaskCommand task = taskQueue.dequeue();
        if (task != null) {
            System.out.println("Task retrieved from queue: " + task);
        } else {
            System.out.println("Queue is empty.");
        }
        return task;
    }

    public boolean isQueueEmpty() {
        return taskQueue.isEmpty();
    }

    public int getQueueSize() {
        return taskQueue.size();
    }
}