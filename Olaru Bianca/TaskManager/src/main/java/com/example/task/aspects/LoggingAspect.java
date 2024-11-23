package com.example.task.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterReturning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @AfterReturning(pointcut = "execution(* com.example.scraper.ScrapingService.scrapeData())", returning = "response")
    public void logFetchingResponse(String response) {
        String message = "Scraping response: " + response;
        logger.info(message);
    }
}