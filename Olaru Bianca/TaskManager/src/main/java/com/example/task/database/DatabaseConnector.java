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
    private final HistoryRecordRepo historyRecordRepo;
    private final ResultRecordRepo resultRecordRepo;

    public DatabaseConnector() {
        EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("JobHunterPU");
        entityManager = entityFactory.createEntityManager();

        dataToExtractRepo = new DataToExtractRepo(entityManager);
        historyRecordRepo = new HistoryRecordRepo(entityManager);
        resultRecordRepo = new ResultRecordRepo(entityManager);
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

        return taskConfigs;
    }

    public void saveHistory(HistoryRecord historyRecord) {
        historyRecordRepo.create(historyRecord);
    }

    public void saveResult(ResultRecord resultRecord) {
        resultRecordRepo.create(resultRecord);
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