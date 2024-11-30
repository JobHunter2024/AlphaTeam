package com.example.task.database;

import jakarta.persistence.EntityManager;
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

    // CRUD Operations
    public void create(DataToExtract newDataToExtract) {
        entityManager.persist(newDataToExtract);
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

    public void update(DataToExtract updatedDataToExtract) {
        entityManager.merge(updatedDataToExtract);
    }

    public void delete(UUID uuid) {
        DataToExtract target = findByUUID(uuid);
        if (target != null) {
            entityManager.remove(target);
        }
    }

    public void deleteByUuid(String uuid) {
        entityManager.createNamedQuery("DataToExtract.deleteByUuid")
                .setParameter("uuid", uuid)
                .executeUpdate();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
