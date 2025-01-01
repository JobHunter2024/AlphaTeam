package com.example.task.queue;

import com.example.scraper.IndeedScraper;
import com.example.scraper.ScrapingService;
import com.example.scraper.ScrapingResult;
import com.example.task.factory.TaskCommand;
import org.springframework.stereotype.Component;
import com.example.task.factory.ScrapeTaskCommand;

import java.net.URL;
import java.util.List;
import java.util.UUID;


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

    public List<ScrapingResult> dispatch() throws IOException
    {
        while (!taskQueueManager.isQueueEmpty())
        {
            TaskCommand task = taskQueueManager.getFromQueue();
            if (task != null)
            {
                ScrapeTaskCommand scrapeTaskCommand = (ScrapeTaskCommand) task;
                String scrapingType=scrapeTaskCommand.getConfig().getType();
                if(scrapingType.equals("scrapeAdvanced"))
                {
                    UUID taskId = scrapeTaskCommand.getTaskId();
                    String url=scrapeTaskCommand.getConfig().getSourceURL();
                    String jobUrlPath=scrapeTaskCommand.getConfig().getJobUrlPath();
                    String jobDescriptionPath=scrapeTaskCommand.getConfig().getJobDescriptionPath();
                    String jobLocationPath=scrapeTaskCommand.getConfig().getJobLocationPath();
                    String jobCompanyPath=scrapeTaskCommand.getConfig().getJobCompanyPath();
                    String jobTitlePath=scrapeTaskCommand.getConfig().getJobTitlePath();
                    String jobDatePath=scrapeTaskCommand.getConfig().getJobDatePath();
                    boolean followLink=scrapeTaskCommand.getConfig().isFollowLink();

                    return scrapingService.scrapeDataAdvanced(taskId, url, jobUrlPath, jobDescriptionPath, jobLocationPath, jobCompanyPath, jobTitlePath, jobDatePath, followLink);
                }
                else if(scrapingType.equals("scrape"))
                {
                    UUID taskId = scrapeTaskCommand.getTaskId();
                    String url = scrapeTaskCommand.getConfig().getSourceURL();
                    String path = scrapeTaskCommand.getConfig().getJsoupPath();

                    return scrapingService.scrapeData(url, path, taskId);
                    //return IndeedScraper.fetchJobListings(task.getTaskId());
                }
            }
        }
        return null;
    }
}