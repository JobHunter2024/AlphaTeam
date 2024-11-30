package com.example.task.processor;

import com.example.task.database.*;
import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import java.net.MalformedURLException;
import java.util.UUID;


@Component
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

    public TaskConfig fetchTaskConfig(long id) {
        DataToExtract dataToExtract = dataToExtractRepo.findById(id);

        if (dataToExtract != null) {
            UUID uuid = dataToExtract.getUuid();
            String sourceURL = dataToExtract.getUrlString();
            String jsoupPath = dataToExtract.getPath();
            String type = "scrape";

            return new TaskConfig(id, uuid, sourceURL, "pending", type, jsoupPath);
        } else {
            System.out.println("DataToExtract not found");
            return null;
        }
    }

    public void saveHistory(HistoryRecord historyRecord) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(historyRecord);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save HistoryRecord", e);
        }
    }

    public void saveResult(ResultRecord resultRecord) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(resultRecord);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save ResultRecord", e);
        }
    }

    public void updateTaskConfig(TaskConfig taskConfig) throws MalformedURLException {
        DataToExtract dataToExtract = dataToExtractRepo.findByUUID(taskConfig.getUUID());

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