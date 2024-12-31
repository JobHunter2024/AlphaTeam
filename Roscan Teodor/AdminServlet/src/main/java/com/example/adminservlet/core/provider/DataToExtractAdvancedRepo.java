package com.example.adminservlet.core.provider;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public class DataToExtractAdvancedRepo {
    @PersistenceUnit
    private EntityManagerFactory entityFactory;

    @PersistenceContext(unitName = "JobHunterPU")
    private EntityManager entityManager;

    public DataToExtractAdvancedRepo() {
        entityFactory = Persistence.createEntityManagerFactory("JobHunterPU");
        entityManager = entityFactory.createEntityManager();
    }


    //CRUD
    @Transactional
    public void create(DataToExtractAdvanced newDataToExtract) {
        entityManager.getTransaction().begin();
        entityManager.persist(newDataToExtract);
        entityManager.getTransaction().commit();
    }

    /*
    public DataToExtractAdvanced findById(Long id) {
        return entityManager.find(DataToExtractAdvanced.class, id);
    }
     */

    public DataToExtractAdvanced findByUUID(UUID uuid) {
        try {
            return entityManager.createNamedQuery("DataToExtractAdvanced.findByUUID", DataToExtractAdvanced.class)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<DataToExtractAdvanced> findAll() {
        return entityManager.createNamedQuery("DataToExtractAdvanced.findAll", DataToExtractAdvanced.class)
                .getResultList();
    }

    @Transactional
    public void update(DataToExtractAdvanced updatedDataToExtract) {
        entityManager.getTransaction().begin();
        entityManager.merge(updatedDataToExtract);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void delete(UUID uuid) {
        DataToExtractAdvanced targetDataToExtract = findByUUID(uuid);
        if (targetDataToExtract != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(targetDataToExtract);
            entityManager.getTransaction().commit();
        }
    }

    /*
    public void deleteByUuid(String uuid) {
        Query query = entityManager.createNamedQuery("DataToExtractAdvanced.deleteByUuid");
        query.setParameter("uuid", uuid);
        query.executeUpdate();
    }
     */
}
