package com.example.task.processor;

import com.example.beta.Sender;
import com.example.scraper.ScrapingResult;
import com.example.scraper.ResponseManager;
import com.example.task.database.DatabaseConnector;
import com.example.task.database.HistoryRecord;
import com.example.task.database.ResultRecord;
import com.example.task.database.ResultRecordAdvanced;
import com.example.task.factory.ScrapeTaskCommand;
import com.example.task.queue.TaskDispatcher;
import com.example.task.queue.TaskQueueManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Scheduled(cron = "0 0 0/12 * * ?")
    public void processAllScrapingTasks() throws IOException {
        List<TaskConfig> allTaskConfigs = databaseConnector.fetchAllTaskConfigs();
        for(TaskConfig taskConfig : allTaskConfigs)
            System.out.println(processScrapingTask(taskConfig));
    }

    public List<String> processScrapingTask(TaskConfig config) throws IOException {
        ScrapeTaskCommand task = new ScrapeTaskCommand(config);

        taskQueueManager.addToQueue(task);
        boolean sendToBeta=config.getType().equals("scrapeAdvanced");
        List<ScrapingResult> resultList = taskDispatcher.dispatch();
        return processResponse(resultList, sendToBeta);
    }

    public List<String> processResponse(List<ScrapingResult> resultList, boolean send)
    {
        if(send)
        {
            Sender sender=new Sender();
            sender.sendToBeta(resultList);
        }

        List<String> resultStringList = new ArrayList<>();

        for(ScrapingResult result : resultList)
        {
            String errorMessage = result.getErrorMessage();
            if (errorMessage == null)
                errorMessage = "No Error";

            HistoryRecord historyRecord = new HistoryRecord(result.getUrl(), result.getPath(), result.getTaskId(), result.success ? "success" : "failure", errorMessage);
            System.out.println("History Record: " + historyRecord);
            databaseConnector.saveHistory(historyRecord);

            if (result.success)
            {
                if(result.getPath().equals("Advanced Path"))
                {
                    ResultRecordAdvanced resultRecordAdvanced = new ResultRecordAdvanced(result.getUrl(), result.getTaskId(), result.getDescription(), result.getLocation(), result.getCompany(), result.getTitle(), result.getDate());
                    databaseConnector.saveResultAdvanced(resultRecordAdvanced);
                }
                else
                {
                    ResultRecord resultRecord = new ResultRecord(result.getUrl(), new Date(), result.getTaskId(), result.getData());
                    databaseConnector.saveResult(resultRecord);
                }

                resultStringList.add(responseManager.processScrapingResult(result));
            }
            else
                System.out.println("Failed to process response for task: " + result.getTaskId());
        }
        return resultStringList;
    }
}