package com.example.beta;

import com.example.scraper.ScrapingResult;
import org.json.JSONObject;
import java.util.List;

public class JsonBuilder {
    public JSONObject build(List<ScrapingResult> resultList) {
        JSONObject jobsJson = new JSONObject();

        for (int i = 0; i < resultList.size(); i++) {
            ScrapingResult result = resultList.get(i);

            JSONObject jobJson = new JSONObject();
            jobJson.put("jobTitle", result.title);
            jobJson.put("company", result.company);
            jobJson.put("date", result.date);
            jobJson.put("location", result.location);
            jobJson.put("jobDescription", result.description);

            jobsJson.put("job_" + String.format("%03d", i), jobJson);
        }

        return jobsJson;
    }
}
