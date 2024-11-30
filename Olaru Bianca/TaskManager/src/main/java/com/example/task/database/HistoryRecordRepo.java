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
public class HistoryRecordRepo {

    @PersistenceContext
    private EntityManager entityManager;

    // CRUD Operations
    public void create(HistoryRecord newHistoryRecord) {
        entityManager.persist(newHistoryRecord);
    }

    public HistoryRecord findById(Long id) {
        return entityManager.find(HistoryRecord.class, id);
    }

    public HistoryRecord findByUUID(UUID uuid) {
        TypedQuery<HistoryRecord> query = entityManager.createNamedQuery("HistoryRecord.findByUUID", HistoryRecord.class);
        query.setParameter("uuid", uuid);
        return query.getResultStream().findFirst().orElse(null);
    }

    public List<HistoryRecord> findAll() {
        return entityManager.createNamedQuery("HistoryRecord.findAll", HistoryRecord.class).getResultList();
    }

    public void update(HistoryRecord updatedHistoryRecord) {
        entityManager.merge(updatedHistoryRecord);
    }

    public void delete(UUID uuid) {
        HistoryRecord target = findByUUID(uuid);
        if (target != null) {
            entityManager.remove(target);
        }
    }

    public void deleteByUuid(String uuid) {
        entityManager.createNamedQuery("HistoryRecord.deleteByUuid")
                .setParameter("uuid", uuid)
                .executeUpdate();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
