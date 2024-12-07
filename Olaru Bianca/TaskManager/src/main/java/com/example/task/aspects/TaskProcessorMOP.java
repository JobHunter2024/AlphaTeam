package com.example.task.aspects;

import com.example.scraper.ScrapingResult;
import com.example.task.processor.TaskConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

@Aspect
@Component
public class TaskProcessorMOP {

    private static final Logger logger = LoggerFactory.getLogger(TaskProcessorMOP.class);

    @Value("${valid.url.prefix}")
    private String validUrlPrefix;

    @Around("execution(* com.example.task.processor.TaskProcessor.processScrapingTask(..))")
    public Object validateScrapingTaskInputs(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof TaskConfig taskConfig) {

                String url = taskConfig.getSourceURL();
                if (url == null || !url.startsWith(validUrlPrefix)) {
                    throw new IllegalArgumentException("URL must start with valid prefix");
                }
            }
        }
        logger.info("Validation successful for processScrapingTask method.");
        return joinPoint.proceed();
    }

    @Around("execution(* com.example.task.processor.TaskProcessor.processResponse(..))")
    public Object validateProcessResponseInputs(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof ScrapingResult result) {

                if (result.getTaskId().toString().length() != 36) {
                    throw new IllegalArgumentException("Valid UUID must have 36 characters");
                }
            }
        }
        logger.info("Validation successful for processResponse method.");
        return joinPoint.proceed();
    }
}