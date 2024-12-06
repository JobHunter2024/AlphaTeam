package com.example.scraper;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.asynchttpclient.Request;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class IndeedScraper {

    public static ScrapingResult fetchJobListings(UUID taskId) throws IOException {
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        ScrapingResult result;

        try {
            String body = "[{\"country\":\"US\",\"domain\":\"indeed.com\",\"keyword_search\":\"data scientist\"}]";

            Request request = client.prepare("POST", "https://indeed-scraper1.p.rapidapi.com/api/b2b/indeed-scraper/job-listings-by-keyword")
                    .setHeader("x-rapidapi-key", "0f5f4cec78msh34737c3c5120e98p1e2516jsn46f596554116")
                    .setHeader("x-rapidapi-host", "indeed-scraper1.p.rapidapi.com")
                    .setHeader("Content-Type", "application/json")
                    .setBody(body)
                    .build();

            Response response = client.executeRequest(request).get();

            if (response.getStatusCode() == 200) {
                String url = "https://indeed-scraper1.p.rapidapi.com/api/b2b/indeed-scraper/job-listings-by-keyword";
                String path = "/api/b2b/indeed-scraper/job-listings-by-keyword";
                String data = response.getResponseBody();
                result = new ScrapingResult(taskId, url, path, data, null, true);
            } else {
                String errorMessage = "Error: " + response.getStatusCode() + " - " + response.getStatusText();
                String url = "https://indeed-scraper1.p.rapidapi.com/api/b2b/indeed-scraper/job-listings-by-keyword";
                String path = "/api/b2b/indeed-scraper/job-listings-by-keyword";
                result = new ScrapingResult(taskId, url, path, null, errorMessage, false);
            }

        } catch (Exception e) {
            String url = "https://indeed-scraper1.p.rapidapi.com/api/b2b/indeed-scraper/job-listings-by-keyword";
            String path = "/api/b2b/indeed-scraper/job-listings-by-keyword";
            String errorMessage = e.getMessage();
            result = new ScrapingResult(taskId, url, path, null, errorMessage, false);
        } finally {
            client.close();
        }

        return result;
    }
}