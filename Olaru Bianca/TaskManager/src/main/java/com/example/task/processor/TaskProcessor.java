package com.example.task.processor;

import com.example.scraper.ScrapingResult;
import com.example.scraper.ResponseManager;
import com.example.task.database.HistoryRecord;
import com.example.task.database.ResultRecord;
import com.example.task.factory.ScrapeTaskCommand;
import com.example.task.queue.TaskDispatcher;
import com.example.task.queue.TaskQueueManager;
import org.springframework.stereotype.Component;

import java.util.Date;
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

    public String processScrapingTask(long id) {
        TaskConfig config = retrieveTaskConfig(id);
        ScrapeTaskCommand task = new ScrapeTaskCommand(config);

        taskQueueManager.addToQueue(task);
        ScrapingResult result = taskDispatcher.dispatch();

        return processResponse(result);
    }

    public TaskConfig retrieveTaskConfig(long taskId) {
        return databaseConnector.fetchTaskConfig(taskId);
    }

    public String processResponse(ScrapingResult result) {
        HistoryRecord historyRecord = new HistoryRecord(result.getUrl(), result.getPath(), result.getTaskId(), result.success ? "success" : "failure");
        databaseConnector.saveHistory(historyRecord);

        if (result.success) {
            ResultRecord resultRecord = new ResultRecord(result.getUrl(), new Date(), result.getTaskId(), result.getData());
            databaseConnector.saveResult(resultRecord);

            return responseManager.processScrapingResult(result);
        } else {
            System.out.println("Failed to process response for task: " + result.getTaskId());
            return null;
        }
    }
}