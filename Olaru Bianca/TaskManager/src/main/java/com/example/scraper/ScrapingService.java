package com.example.scraper;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScrapingService {

    public ScrapingResult scrapeData(String url, String path, UUID taskId) {
        String content = scrapeContentFromURL(url, path);
        if (content == null || content.isEmpty()) {
            return new ScrapingResult(taskId, url, path, null, "No data found", false);
        }
        return new ScrapingResult(taskId, url, path, content, null, true);
    }

    private String scrapeContentFromURL(String url, String path) {
        try {
            Document doc = org.jsoup.Jsoup.connect(url).get();
            return doc.select(path).text();
        } catch (Exception e) {
            return null;
        }
    }
}