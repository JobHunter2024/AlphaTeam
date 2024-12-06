package com.example.task.processor;

import com.example.scraper.ScrapingResult;
import com.example.scraper.ResponseManager;
import com.example.task.database.DatabaseConnector;
import com.example.task.database.HistoryRecord;
import com.example.task.database.ResultRecord;
import com.example.task.factory.ScrapeTaskCommand;
import com.example.task.queue.TaskDispatcher;
import com.example.task.queue.TaskQueueManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;


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

    @Scheduled(cron = "0 0 0/12 * * ?")
    public void processAllScrapingTasks() throws IOException {
        List<TaskConfig> allTaskConfigs = databaseConnector.fetchAllTaskConfigs();
        for(TaskConfig taskConfig : allTaskConfigs)
            System.out.println(processScrapingTask(taskConfig));
    }

    public String processScrapingTask(TaskConfig config) throws IOException {
        ScrapeTaskCommand task = new ScrapeTaskCommand(config);

        taskQueueManager.addToQueue(task);
        ScrapingResult result = taskDispatcher.dispatch();
        System.out.println("Scraping result: " + result.toString());
        return processResponse(result);
    }

    public String processResponse(ScrapingResult result) {
        String errorMessage = result.getErrorMessage();
        if (errorMessage == null)
            errorMessage = "No Error";

        HistoryRecord historyRecord = new HistoryRecord(result.getUrl(), result.getPath(), result.getTaskId(), result.success ? "success" : "failure", errorMessage);
        System.out.println("History Record: " + historyRecord);
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