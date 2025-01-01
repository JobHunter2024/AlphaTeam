package com.example.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ScrapingService {

    public List<ScrapingResult> scrapeData(String url, String path, UUID taskId) {
        List<ScrapingResult> resultList=new ArrayList<ScrapingResult>();
        String content = scrapeContentFromURL(url, path);

        if (content == null || content.isEmpty())
            resultList.add(new ScrapingResult(taskId, url, path, null, "No data found", false));
        else
            resultList.add(new ScrapingResult(taskId, url, path, content, null, true));

        return resultList;
    }

    public List<ScrapingResult> scrapeDataAdvanced(UUID taskId, String url, String jobUrlPath, String jobDescriptionPath, String jobLocationPath, String jobCompanyPath, String jobTitlePath, String jobDatePath, boolean followLink) {
        List<ScrapingResult> resultList = new ArrayList<>();
        try {
            Document doc = org.jsoup.Jsoup.connect(url).get();
            Elements jobLinkElements = doc.select(jobUrlPath);

            if (jobLinkElements.isEmpty()) {
                resultList.add(new ScrapingResult(taskId, url, jobUrlPath, null, "Job links not found", false));
            } else {
                for (Element jobLinkElement : jobLinkElements) {
                    String jobPageUrl = followLink ? jobLinkElement.absUrl("href") : url;
                    Document jobPageDoc = org.jsoup.Jsoup.connect(jobPageUrl).get();

                    String description = getElementTextOrDefault(jobPageDoc, jobDescriptionPath, "Not found");
                    String location = getElementTextOrDefault(jobPageDoc, jobLocationPath, "Not found");
                    String company = getElementTextOrDefault(jobPageDoc, jobCompanyPath, "Not found");
                    String title = getElementTextOrDefault(jobPageDoc, jobTitlePath, "Not found");
                    String date = getElementTextOrDefault(jobPageDoc, jobDatePath, "Not found");

                    resultList.add(new ScrapingResult(taskId, jobPageUrl, "Advanced Path", "No Error", true, description, location, company, title, date));
                }
            }
        } catch (Exception e) {
            resultList.add(new ScrapingResult(taskId, url, jobUrlPath, null, "Error during scraping: " + e.getMessage(), false));
        }
        return resultList;
    }

    private String scrapeContentFromURL(String url, String path) {
        try {
            Document doc = org.jsoup.Jsoup.connect(url).get();
            return doc.select(path).text();
        } catch (Exception e) {
            return null;
        }
    }

    private String getElementTextOrDefault(Document doc, String path, String defaultText) {
        Element element = doc.selectFirst(path);
        return (element != null) ? element.text() : defaultText;
    }
}