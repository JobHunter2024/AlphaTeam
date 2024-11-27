package com.example.task.processor;

import com.example.task.database.DataToExtract;
import com.example.task.database.DataToExtractRepo;
import com.example.task.database.HistoryRecordRepo;
import com.example.task.database.ResultRecordRepo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

public class DatabaseConnector {

    private EntityManager entityManager;
    private DataToExtractRepo dataToExtractRepo;
    private HistoryRecordRepo historyRecordRepo;
    private ResultRecordRepo resultRecordRepo;

    public DatabaseConnector() {
        EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("JobHunterPU");
        this.entityManager = entityFactory.createEntityManager();
        this.dataToExtractRepo = new DataToExtractRepo();
        this.historyRecordRepo = new HistoryRecordRepo();
        this.resultRecordRepo = new ResultRecordRepo();

        dataToExtractRepo.setEntityManager(entityManager);
        historyRecordRepo.setEntityManager(entityManager);
        resultRecordRepo.setEntityManager(entityManager);
    }

    public TaskConfig fetchTaskConfig(UUID uuid) {
        DataToExtract dataToExtract = dataToExtractRepo.findByUUID(uuid);

        if (dataToExtract != null) {
            String sourceURL = dataToExtract.getUrlString();
            String jsoupPath = dataToExtract.getPath();
            String type = "scrape";

            return new TaskConfig(uuid, sourceURL, "pending", type, jsoupPath);
        } else {
            return null;
        }
    }

    public void updateTaskConfig(TaskConfig taskConfig) throws MalformedURLException {
        DataToExtract dataToExtract = dataToExtractRepo.findByUUID(taskConfig.getTaskId());

        if (dataToExtract != null) {
            dataToExtract.setUrlString(taskConfig.getSourceURL());
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