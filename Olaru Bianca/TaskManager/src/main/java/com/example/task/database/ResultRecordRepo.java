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
public class ResultRecordRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public ResultRecordRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // CRUD Operations
    @Transactional
    public void create(ResultRecord newResultRecord) {
        entityManager.getTransaction().begin();
        entityManager.persist(newResultRecord);
        entityManager.getTransaction().commit();
    }

    public ResultRecord findById(Long id) {
        return entityManager.find(ResultRecord.class, id);
    }

    public ResultRecord findByUUID(UUID uuid) {
        TypedQuery<ResultRecord> query = entityManager.createNamedQuery("ResultRecord.findByUUID", ResultRecord.class);
        query.setParameter("uuid", uuid);
        return query.getResultStream().findFirst().orElse(null);
    }

    public List<ResultRecord> findAll() {
        return entityManager.createNamedQuery("ResultRecord.findAll", ResultRecord.class).getResultList();
    }

    @Transactional
    public void update(ResultRecord updatedResultRecord) {
        entityManager.getTransaction().begin();
        entityManager.merge(updatedResultRecord);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void delete(UUID uuid) {
        ResultRecord target = findByUUID(uuid);
        if (target != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(target);
            entityManager.getTransaction().commit();
        }
    }

    public void deleteByUuid(String uuid) {
        entityManager.createNamedQuery("ResultRecord.deleteByUUID")
                .setParameter("uuid", uuid)
                .executeUpdate();
    }
}
