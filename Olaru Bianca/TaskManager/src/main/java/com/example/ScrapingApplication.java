package com.example;

import com.example.task.processor.TaskProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class ScrapingApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ScrapingApplication.class, args);
        TaskProcessor taskProcessor = context.getBean(TaskProcessor.class);
        taskProcessor.processAllScrapingTasks();
    }
}
