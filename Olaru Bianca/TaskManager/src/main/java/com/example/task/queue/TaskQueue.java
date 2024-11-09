package com.example.task.queue;

import com.example.task.factory.TaskCommand;
import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {
    private final Queue<TaskCommand> queue;

    public TaskQueue() {
        this.queue = new LinkedList<>();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public void enqueue(TaskCommand taskCommand) {
        queue.offer(taskCommand);
    }

    public TaskCommand dequeue() {
        return queue.poll();
    }

    public TaskCommand peek() {
        return queue.peek();
    }
}