package com.example;

import com.example.task.processor.TaskProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class ScrapingApplication {

    public static void main(String[] args) {
        System.out.println("Working");
        ApplicationContext context = SpringApplication.run(ScrapingApplication.class, args);
        System.out.println("Working");
        TaskProcessor taskProcessor = context.getBean(TaskProcessor.class);

        long mockTaskID = 1;
        String response = taskProcessor.processScrapingTask(mockTaskID);

        System.out.println(response);
    }
}
