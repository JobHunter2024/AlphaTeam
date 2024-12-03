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

    public HistoryRecordRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // CRUD Operations
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
        TypedQuery<HistoryRecord> query = entityManager.createNamedQuery("HistoryRecord.findByUUID", HistoryRecord.class);
        query.setParameter("uuid", uuid);
        return query.getResultStream().findFirst().orElse(null);
    }

    public List<HistoryRecord> findAll() {
        return entityManager.createNamedQuery("HistoryRecord.findAll", HistoryRecord.class).getResultList();
    }

    @Transactional
    public void update(HistoryRecord updatedHistoryRecord) {
        entityManager.getTransaction().begin();
        entityManager.merge(updatedHistoryRecord);
        entityManager.getTransaction().commit();
    }

    @Transactional
    public void delete(UUID uuid) {
        HistoryRecord target = findByUUID(uuid);
        if (target != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(target);
            entityManager.getTransaction().commit();
        }
    }

    public void deleteByUuid(String uuid) {
        entityManager.createNamedQuery("HistoryRecord.deleteByUuid")
                .setParameter("uuid", uuid)
                .executeUpdate();
    }
}
