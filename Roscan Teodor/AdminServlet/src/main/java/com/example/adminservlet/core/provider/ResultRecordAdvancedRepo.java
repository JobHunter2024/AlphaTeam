package com.example.adminservlet.core.provider;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public class ResultRecordAdvancedRepo {
    @PersistenceUnit
    private EntityManagerFactory entityFactory;

    @PersistenceContext(unitName = "JobHunterPU")
    private EntityManager entityManager;

    public ResultRecordAdvancedRepo() {
        entityFactory = Persistence.createEntityManagerFactory("JobHunterPU");
        entityManager = entityFactory.createEntityManager();
    }


    //CRUD
    @Transactional
    public void create(ResultRecordAdvanced newResultRecordAdvanced) {
        entityManager.getTransaction().begin();
        entityManager.persist(newResultRecordAdvanced);
        entityManager.getTransaction().commit();
    }

    /*
    public ResultRecordAdvanced findById(Long id) {
        return entityManager.find(ResultRecordAdvanced.class, id);
    }
     */

    public ResultRecordAdvanced findByUUID(UUID uuid) {
        try {
            return entityManager.createNamedQuery("ResultRecordAdvanced.findByUUID", ResultRecordAdvanced.class)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<ResultRecordAdvanced> findAll() {
        return entityManager.createNamedQuery("ResultRecordAdvanced.findAll", ResultRecordAdvanced.class)
                .getResultList();
    }

    @Transactional
    public void update(ResultRecordAdvanced updatedResultRecordAdvanced) {
        entityManager.getTransaction().begin();
        entityManager.merge(updatedResultRecordAdvanced);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void delete(UUID uuid) {
        ResultRecordAdvanced targetResultRecordAdvanced = findByUUID(uuid);
        if (targetResultRecordAdvanced != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(targetResultRecordAdvanced);
            entityManager.getTransaction().commit();
        }
    }

    @Transactional
    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM ResultRecordAdvanced").executeUpdate();
        entityManager.getTransaction().commit();
    }

    /*
    public void deleteByUuid(String uuid) {
        Query query = entityManager.createNamedQuery("ResultRecordAdvanced.deleteByUUID");
        query.setParameter("uuid", uuid);
        query.executeUpdate();
    }
     */
}
