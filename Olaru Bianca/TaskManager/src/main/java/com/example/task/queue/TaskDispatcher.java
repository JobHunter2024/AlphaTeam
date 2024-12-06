package com.example.task.queue;

import com.example.scraper.IndeedScraper;
import com.example.scraper.ScrapingService;
import com.example.scraper.ScrapingResult;
import com.example.task.factory.TaskCommand;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class TaskDispatcher {
    private final TaskQueueManager taskQueueManager;
    private final ScrapingService scrapingService;

    public TaskDispatcher(TaskQueueManager taskQueueManager,
                          ScrapingService scrapingService) {
        this.taskQueueManager = taskQueueManager;
        this.scrapingService = scrapingService;
    }

    public ScrapingResult dispatch() throws IOException {
        while (!taskQueueManager.isQueueEmpty()) {
            TaskCommand task = taskQueueManager.getFromQueue();
            if (task != null) {
//                if (task instanceof ScrapeTaskCommand scrapeTaskCommand) {
//                    UUID taskId = scrapeTaskCommand.getTaskId();
//                    String url = scrapeTaskCommand.getConfig().getSourceURL();
//                    String path = scrapeTaskCommand.getConfig().getJsoupPath();
//
//                    return scrapingService.scrapeData(url, path, taskId);
                return IndeedScraper.fetchJobListings(task.getTaskId());
                }
            }
        return null;
    }
}