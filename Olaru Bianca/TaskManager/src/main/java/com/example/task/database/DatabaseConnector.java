package com.example.task.database;

import com.example.task.processor.TaskConfig;
import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
public class DatabaseConnector {

    private EntityManager entityManager;
    private final DataToExtractRepo dataToExtractRepo;
    private final DataToExtractAdvancedRepo dataToExtractAdvancedRepo;
    private final HistoryRecordRepo historyRecordRepo;
    private final ResultRecordRepo resultRecordRepo;
    private final ResultRecordAdvancedRepo resultRecordAdvancedRepo;

    public DatabaseConnector() {
        EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("JobHunterPU");
        entityManager = entityFactory.createEntityManager();

        dataToExtractRepo = new DataToExtractRepo(entityManager);
        dataToExtractAdvancedRepo=new DataToExtractAdvancedRepo(entityManager);
        historyRecordRepo = new HistoryRecordRepo(entityManager);
        resultRecordRepo = new ResultRecordRepo(entityManager);
        resultRecordAdvancedRepo = new ResultRecordAdvancedRepo(entityManager);
    }

    public TaskConfig fetchTaskConfig(long id) {
        DataToExtract dataToExtract = dataToExtractRepo.findById(id);

        if (dataToExtract != null) {
            UUID uuid = dataToExtract.getUuid();
            String sourceURL = dataToExtract.getUrl();
            String jsoupPath = dataToExtract.getPath();
            String type = "scrape";

            return new TaskConfig(id, uuid, sourceURL, "pending", type, jsoupPath);
        } else {
            System.out.println("DataToExtract not found");
            return null;
        }
    }

    public List<TaskConfig> fetchAllTaskConfigs(){
        List<DataToExtract> dataToExtractList = dataToExtractRepo.findAll();
        List<DataToExtractAdvanced> dataToExtractAdvancedList = dataToExtractAdvancedRepo.findAll();

        List<TaskConfig> taskConfigs = new ArrayList<>();

        for(DataToExtract dataToExtract : dataToExtractList){
            if(dataToExtract != null) {
                long id=dataToExtract.getId();
                UUID uuid = dataToExtract.getUuid();
                String sourceURL = dataToExtract.getUrl();
                String jsoupPath = dataToExtract.getPath();
                String type = "scrape";

                taskConfigs.add(new TaskConfig(id, uuid, sourceURL, "pending", type, jsoupPath));
            }
        }

        for(DataToExtractAdvanced dataToExtractAdvanced : dataToExtractAdvancedList){
            if(dataToExtractAdvanced != null) {
                long id=dataToExtractAdvanced.getId();
                UUID uuid = dataToExtractAdvanced.getUuid();

                String url=dataToExtractAdvanced.getUrl();
                String jobUrlPath=dataToExtractAdvanced.getJobUrlPath();
                String jobDescriptionPath=dataToExtractAdvanced.getJobDescriptionPath();
                String jobLocationPath=dataToExtractAdvanced.getJobLocationPath();
                String jobCompanyPath=dataToExtractAdvanced.getJobCompanyPath();
                String jobTitlePath=dataToExtractAdvanced.getJobTitlePath();
                String jobDatePath=dataToExtractAdvanced.getJobDatePath();
                boolean followLink=dataToExtractAdvanced.getFollowLink();

                String type = "scrapeAdvanced";

                taskConfigs.add(new TaskConfig(id, uuid, url, "pending", type, jobUrlPath, jobDescriptionPath, jobLocationPath, jobCompanyPath, jobTitlePath, jobDatePath, followLink));
            }
        }

        return taskConfigs;
    }

    public void saveHistory(HistoryRecord historyRecord) {
        historyRecordRepo.create(historyRecord);
    }

    public void saveResult(ResultRecord resultRecord) {
        resultRecordRepo.create(resultRecord);
    }

    public void saveResultAdvanced(ResultRecordAdvanced resultRecordAdvanced) {
        resultRecordAdvancedRepo.create(resultRecordAdvanced);
    }

    public void updateTaskConfig(TaskConfig taskConfig) throws MalformedURLException {
        DataToExtract dataToExtract = dataToExtractRepo.findByUUID(taskConfig.getUUID());

        if (dataToExtract != null) {
            dataToExtract.setUrl(taskConfig.getSourceURL());
            dataToExtract.setPath(taskConfig.getJsoupPath());

            dataToExtractRepo.update(dataToExtract);
        }
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}