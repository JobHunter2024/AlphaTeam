package com.example.beta;

import com.example.scraper.ScrapingResult;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Sender {
    private JsonBuilder jsonBuilder;

    public Sender() {
        jsonBuilder = new JsonBuilder();
    }

    public String sendToBeta(List<ScrapingResult> resultList) {
        JSONObject jsonFile = jsonBuilder.build(resultList);
        saveJson(jsonFile, "C:\\Users\\Teodor\\Desktop\\Result.json");

        try {
            URL url = new URL("http://localhost:8000/api/triples/store");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonFile.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return "Data sent successfully!";
            } else {
                return "Failed to send data. Response code: " + responseCode;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error during request: " + e.getMessage();
        }
    }

    public void saveJson(JSONObject jsonFile, String destination) {
        try {
            // Create the directory if it doesn't exist
            File file = new File(destination);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();  // Create the directory structure if needed
            }

            // Write the JSON object to the file
            try (FileWriter fileWriter = new FileWriter(destination)) {
                fileWriter.write(jsonFile.toString(4));  // 4 is the indentation level
                System.out.println("Successfully saved JSON to " + destination);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving JSON to file: " + e.getMessage());
        }
    }
}
