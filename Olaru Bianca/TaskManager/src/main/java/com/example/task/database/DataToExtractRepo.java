package com.example.task.database;

import com.example.task.database.DataToExtract;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.UUID;

public class DataToExtractRepo {
    @PersistenceUnit
    private EntityManagerFactory entityFactory;

    @PersistenceContext(unitName = "JobHunterPU")
    private EntityManager entityManager;

    public DataToExtractRepo() {
        entityFactory = Persistence.createEntityManagerFactory("JobHunterPU");
        entityManager = entityFactory.createEntityManager();
    }

    @Transactional
    public void create(DataToExtract newDataToExtract) {
        entityManager.getTransaction().begin();
        entityManager.persist(newDataToExtract);
        entityManager.getTransaction().commit();
    }

    public DataToExtract findById(Long id) {
        return entityManager.find(DataToExtract.class, id);
    }

    @Transactional
    public void update(DataToExtract updatedDataToExtract) {
        entityManager.getTransaction().begin();
        entityManager.merge(updatedDataToExtract);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void delete(UUID uuid) {
        DataToExtract targetDataToExtract = findByUUID(uuid);
        if (targetDataToExtract != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(targetDataToExtract);
            entityManager.getTransaction().commit();
        }
    }

    public DataToExtract findByUUID(UUID uuid) {
        try {
            return entityManager.createNamedQuery("DataToExtract.findByUUID", DataToExtract.class)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
