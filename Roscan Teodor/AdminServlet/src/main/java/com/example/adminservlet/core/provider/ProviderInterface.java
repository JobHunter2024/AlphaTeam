package com.example.adminservlet.core.provider;

import com.example.adminservlet.core.data.extraction.DataToExtract;
import com.example.adminservlet.core.database.DataToExtractCRUD;
import com.example.adminservlet.core.database.HistoryRecordCRUD;
import com.example.adminservlet.core.database.ResultRecordCRUD;
import org.json.JSONObject;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.*;

public class ProviderInterface {
    public DataToExtractCRUD dataToExtractCRUD;
    public HistoryRecordCRUD historyRecordCRUD;
    public ResultRecordCRUD resultRecordCRUD;

    public ProviderInterface() {

    }

    public ProviderInterface(DataToExtractCRUD dataToExtractCRUD) {
        this.dataToExtractCRUD = dataToExtractCRUD;
        this.historyRecordCRUD = new HistoryRecordCRUD();
        this.resultRecordCRUD = new ResultRecordCRUD();
    }

    public List<HistoryRecord> getScrappingHistory(){
        return historyRecordCRUD.getAllData();
    }

    public List<ResultRecord> getScrappingResults(){
        return resultRecordCRUD.getAllData();
    }

    public List<DataToExtract> getScraperConfig()
    {
        return dataToExtractCRUD.getAllData();
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
