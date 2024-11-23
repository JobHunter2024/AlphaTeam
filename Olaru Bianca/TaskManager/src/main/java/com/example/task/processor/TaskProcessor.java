package com.example.task.processor;

import com.example.scraper.ScrapingResult;
import com.example.scraper.ResponseManager;
import com.example.task.factory.ScrapeTaskCommand;
import com.example.task.queue.TaskDispatcher;
import com.example.task.queue.TaskQueueManager;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TaskProcessor {

    private final DatabaseConnector databaseConnector;
    private final ResponseManager responseManager;
    private final TaskDispatcher taskDispatcher;
    private final TaskQueueManager taskQueueManager;

    public TaskProcessor(DatabaseConnector databaseConnector,
                         ResponseManager responseManager, TaskDispatcher taskDispatcher, TaskQueueManager taskQueueManager) {
        this.databaseConnector = databaseConnector;
        this.responseManager = responseManager;
        this.taskDispatcher = taskDispatcher;
        this.taskQueueManager = taskQueueManager;
    }

    public String processTask(UUID id) {
        TaskConfig config = retrieveTaskConfig(id);
        ScrapeTaskCommand task = new ScrapeTaskCommand(config);

        taskQueueManager.addToQueue(task);
        ScrapingResult result = taskDispatcher.dispatch();

        return processResponse(result);
    }

    public TaskConfig retrieveTaskConfig(UUID taskId) {
        return databaseConnector.fetchTaskConfig(taskId);
    }

    public String processResponse(ScrapingResult result) {
        if (result.success) {
            return responseManager.processScrapingResult(result);
        } else {
            System.out.println("Failed to process response for task: " + result.getTaskId());
            return null;
        }
    }
}