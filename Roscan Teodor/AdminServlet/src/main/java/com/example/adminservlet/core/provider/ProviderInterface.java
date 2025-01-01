package com.example.adminservlet.core.provider;

import com.example.adminservlet.core.database.*;
import org.json.JSONObject;

import java.util.*;

public class ProviderInterface {
    public DataToExtractCRUD dataToExtractCRUD;
    public DataToExtractAdvancedCRUD dataToExtractAdvancedCRUD;
    public HistoryRecordCRUD historyRecordCRUD;
    public ResultRecordCRUD resultRecordCRUD;
    public ResultRecordAdvancedCRUD resultRecordAdvancedCRUD;

    public ProviderInterface() {

    }

    public ProviderInterface(DataToExtractCRUD dataToExtractCRUD, DataToExtractAdvancedCRUD dataToExtractAdvancedCRUD) {
        this.dataToExtractCRUD = dataToExtractCRUD;
        this.dataToExtractAdvancedCRUD = dataToExtractAdvancedCRUD;
        this.historyRecordCRUD = new HistoryRecordCRUD();
        this.resultRecordCRUD = new ResultRecordCRUD();
        this.resultRecordAdvancedCRUD = new ResultRecordAdvancedCRUD();
    }

    public List<HistoryRecord> getScrappingHistory(){
        return historyRecordCRUD.getAllData();
    }

    public List<ResultRecord> getScrappingResults(){
        return resultRecordCRUD.getAllData();
    }

    public List<ResultRecordAdvanced> getScrappingResultsAdvanced(){ return resultRecordAdvancedCRUD.getAllData(); }

    public List<DataToExtract> getScraperConfig()
    {
        return dataToExtractCRUD.getAllData();
    }

    public List<DataToExtractAdvanced> getScraperConfigAdvanced()
    {
        return dataToExtractAdvancedCRUD.getAllData();
    }

    public void deleteHistory(){
        historyRecordCRUD.removeAllData();
    }

    public void deleteResults(){
        resultRecordCRUD.removeAllData();
    }

    public void deleteResultsAdvanced() {
        resultRecordAdvancedCRUD.removeAllData();
    }

    public JSONObject getScrappingStatistics(){
        return null;
    }

    public void historyMockery() {

        String path="div1 > div2 > div3 > div4";

        List<HistoryRecord> historyRecords = Arrays.asList(
                new HistoryRecord("https://www.youtube.com/watch?v=abcd1234", path, UUID.randomUUID(), "success", "No Error"),
                new HistoryRecord("https://www.linkedin.com/in/johndoe/", path, UUID.randomUUID(), "success", "No Error"),
                new HistoryRecord("https://github.com/sample/repo", path, UUID.randomUUID(), "success", "No Error"),
                new HistoryRecord("https://www.medium.com/article-xyz", path, UUID.randomUUID(), "fail", "No data found"),
                new HistoryRecord("https://www.reddit.com/r/programming/", path, UUID.randomUUID(), "pending", "No Error")
        );

        historyRecordCRUD.listToRows(historyRecords);
    }

    public void resultMockery(){
        List<ResultRecord> resultRecords = Arrays.asList(
                new ResultRecord("https://www.google.com/search?q=hibernate", new Date(),
                        UUID.randomUUID(),
                        "{\"query\": \"hibernate\", \"results\": 20}"),

                new ResultRecord("https://www.stackoverflow.com/questions/12345", new Date(),
                        UUID.randomUUID(),
                        "{\"question\": \"How to use Hibernate?\", \"answers\": 5}"),

                new ResultRecord("https://www.wikipedia.org/wiki/Java_(programming_language)", new Date(),
                        UUID.randomUUID(),
                        "{\"title\": \"Java Programming\", \"summary\": \"Java is a high-level language.\"}"),

                new ResultRecord("https://www.youtube.com/watch?v=abcd5678", new Date(),
                        UUID.randomUUID(),
                        "{\"videoTitle\": \"Hibernate Tutorial\", \"channel\": \"Tech Guru\"}"),

                new ResultRecord("https://www.medium.com/@dev/hql-vs-sql", new Date(),
                        UUID.randomUUID(),
                        "{\"articleTitle\": \"HQL vs SQL\", \"author\": \"Jane Dev\"}"),

                new ResultRecord("https://www.reddit.com/r/learnprogramming/", new Date(),
                        UUID.randomUUID(),
                        "{\"subreddit\": \"learnprogramming\", \"topic\": \"Hibernate Tips\"}")
        );

        resultRecordCRUD.listToRows(resultRecords);
    }
}
