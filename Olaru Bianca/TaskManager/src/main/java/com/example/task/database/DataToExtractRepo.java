package com.example.task.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class DataToExtractRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public DataToExtractRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // CRUD Operations
    @Transactional
    public void create(DataToExtract newDataToExtract) {
        entityManager.getTransaction().begin();
        entityManager.persist(newDataToExtract);
        entityManager.getTransaction().commit();
    }

    public DataToExtract findById(Long id) {
        return entityManager.find(DataToExtract.class, id);
    }

    public DataToExtract findByUUID(UUID uuid) {
        TypedQuery<DataToExtract> query = entityManager.createNamedQuery("DataToExtract.findByUUID", DataToExtract.class);
        query.setParameter("uuid", uuid);
        return query.getResultStream().findFirst().orElse(null);
    }

    public List<DataToExtract> findAll() {
        return entityManager.createNamedQuery("DataToExtract.findAll", DataToExtract.class).getResultList();
    }

    @Transactional
    public void update(DataToExtract updatedDataToExtract) {
        entityManager.getTransaction().begin();
        entityManager.merge(updatedDataToExtract);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void delete(UUID uuid) {
        DataToExtract target = findByUUID(uuid);
        if (target != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(target);
            entityManager.getTransaction().commit();
        }
    }

    public void deleteByUuid(String uuid) {
        entityManager.createNamedQuery("DataToExtract.deleteByUuid")
                .setParameter("uuid", uuid)
                .executeUpdate();
    }
}
