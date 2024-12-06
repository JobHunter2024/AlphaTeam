package com.example;

import com.example.task.processor.TaskProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;


@SpringBootApplication
@EnableScheduling
public class ScrapingApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = SpringApplication.run(ScrapingApplication.class, args);
        TaskProcessor taskProcessor = context.getBean(TaskProcessor.class);
        taskProcessor.processAllScrapingTasks();
    }
}
