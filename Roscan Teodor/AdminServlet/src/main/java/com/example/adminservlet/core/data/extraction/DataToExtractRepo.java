package com.example.adminservlet.core.data.extraction;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
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

    public void deleteByUuid(String uuid)
    {
        Query query = entityManager.createNamedQuery("DataToExtract.deleteByUuid");
        query.setParameter("uuid", uuid);
        query.executeUpdate();
    }

    public List<DataToExtract> findAll() {
        return entityManager.createNamedQuery("DataToExtract.findAll", DataToExtract.class)
                .getResultList();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
