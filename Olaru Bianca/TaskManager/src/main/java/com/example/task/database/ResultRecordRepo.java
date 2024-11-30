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

    // CRUD Operations
    public void create(ResultRecord newResultRecord) {
        entityManager.persist(newResultRecord);
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

    public void update(ResultRecord updatedResultRecord) {
        entityManager.merge(updatedResultRecord);
    }

    public void delete(UUID uuid) {
        ResultRecord target = findByUUID(uuid);
        if (target != null) {
            entityManager.remove(target);
        }
    }

    public void deleteByUuid(String uuid) {
        entityManager.createNamedQuery("ResultRecord.deleteByUUID")
                .setParameter("uuid", uuid)
                .executeUpdate();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
