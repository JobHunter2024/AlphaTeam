package com.example.task.processor;

import com.example.task.database.DataToExtract;
import com.example.task.database.DataToExtractRepo;

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

    public DatabaseConnector() {
        EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("JobHunterPU");
        this.entityManager = entityFactory.createEntityManager();
        this.dataToExtractRepo = new DataToExtractRepo();
        dataToExtractRepo.setEntityManager(entityManager);
    }

    public TaskConfig fetchTaskConfig(UUID uuid) {
        DataToExtract dataToExtract = dataToExtractRepo.findByUUID(uuid);

        if (dataToExtract != null) {
            String sourceURL = dataToExtract.getUrl().toString();
            Map<String, String> jsoupPath = dataToExtract.getPath();
            String type = "scrape";

            return new TaskConfig(uuid, sourceURL, "pending", type, jsoupPath);
        } else {
            return null;
        }
    }

    public void updateTaskConfig(TaskConfig taskConfig) throws MalformedURLException {
        DataToExtract dataToExtract = dataToExtractRepo.findByUUID(taskConfig.getId());
        if (dataToExtract != null) {
            dataToExtract.setURL(new URL(taskConfig.getSourceURL()));
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