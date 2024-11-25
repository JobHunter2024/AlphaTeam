package com.example.adminservlet.core.provider;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public class ResultRecordRepo {
    @PersistenceUnit
    private EntityManagerFactory entityFactory;

    @PersistenceContext(unitName = "JobHunterPU")
    private EntityManager entityManager;

    public ResultRecordRepo() {
        entityFactory = Persistence.createEntityManagerFactory("JobHunterPU");
        entityManager = entityFactory.createEntityManager();
    }

    @Transactional
    public void create(ResultRecord newResultRecord) {
        entityManager.getTransaction().begin();
        entityManager.persist(newResultRecord);
        entityManager.getTransaction().commit();
    }

    public ResultRecord findById(Long id) {
        return entityManager.find(ResultRecord.class, id);
    }

    @Transactional
    public void update(ResultRecord updatedResultRecord) {
        entityManager.getTransaction().begin();
        entityManager.merge(updatedResultRecord);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void delete(UUID uuid) {
        ResultRecord targetResultRecord = findByUUID(uuid);
        if (targetResultRecord != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(targetResultRecord);
            entityManager.getTransaction().commit();
        }
    }

    public ResultRecord findByUUID(UUID uuid) {
        try {
            return entityManager.createNamedQuery("ResultRecord.findByUUID", ResultRecord.class)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void deleteByUuid(String uuid)
    {
        Query query = entityManager.createNamedQuery("ResultRecord.deleteByUUID");
        query.setParameter("uuid", uuid);
        query.executeUpdate();
    }

    public List<ResultRecord> findAll() {
        return entityManager.createNamedQuery("ResultRecord.findAll", ResultRecord.class)
                .getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
