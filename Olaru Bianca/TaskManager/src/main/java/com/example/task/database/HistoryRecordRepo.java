package com.example.task.database;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public class HistoryRecordRepo {
    @PersistenceUnit
    private EntityManagerFactory entityFactory;

    @PersistenceContext(unitName = "JobHunterPU")
    private EntityManager entityManager;

    public HistoryRecordRepo() {
        entityFactory = Persistence.createEntityManagerFactory("JobHunterPU");
        entityManager = entityFactory.createEntityManager();
    }


    //CRUD
    @Transactional
    public void create(HistoryRecord newHistoryRecord) {
        entityManager.getTransaction().begin();
        entityManager.persist(newHistoryRecord);
        entityManager.getTransaction().commit();
    }

    public HistoryRecord findById(Long id) {
        return entityManager.find(HistoryRecord.class, id);
    }

    public HistoryRecord findByUUID(UUID uuid) {
        try {
            return entityManager.createNamedQuery("HistoryRecord.findByUUID", HistoryRecord.class)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<HistoryRecord> findAll() {
        return entityManager.createNamedQuery("HistoryRecord.findAll", HistoryRecord.class)
                .getResultList();
    }

    @Transactional
    public void update(HistoryRecord updatedHistoryRecord) {
        entityManager.getTransaction().begin();
        entityManager.merge(updatedHistoryRecord);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void delete(UUID uuid) {
        HistoryRecord targetHistoryRecord = findByUUID(uuid);
        if (targetHistoryRecord != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(targetHistoryRecord);
            entityManager.getTransaction().commit();
        }
    }

    public void deleteByUuid(String uuid) {
        Query query = entityManager.createNamedQuery("HistoryRecord.deleteByUuid");
        query.setParameter("uuid", uuid);
        query.executeUpdate();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


}
