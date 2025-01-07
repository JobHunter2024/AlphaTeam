package com.example.adminservlet.core.provider;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ModificationRepo {
    @PersistenceUnit
    private EntityManagerFactory entityFactory;

    @PersistenceContext(unitName = "JobHunterPU")
    private EntityManager entityManager;

    public ModificationRepo() {
        entityFactory = Persistence.createEntityManagerFactory("JobHunterPU");
        entityManager = entityFactory.createEntityManager();
    }


    //CRUD
    @Transactional
    public void create(ModificationRecord newModificationRecord) {
        entityManager.getTransaction().begin();
        entityManager.persist(newModificationRecord);
        entityManager.getTransaction().commit();
    }

    public List<ModificationRecord> findAll() {
        return entityManager.createNamedQuery("ModificationRecord.findAll", ModificationRecord.class)
                .getResultList();
    }

    public List<ModificationRecord> findBySection(String section) {
        try {
            return entityManager.createNamedQuery("ModificationRecord.findBySection", ModificationRecord.class)
                    .setParameter("section", section)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<ModificationRecord> findByUsername(String username) {
        try {
            return entityManager.createNamedQuery("ModificationRecord.findByUsername", ModificationRecord.class)
                    .setParameter("username", username)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<ModificationRecord> findByDate(Date date) {
        try {
            return entityManager.createNamedQuery("ModificationRecord.findByDate", ModificationRecord.class)
                    .setParameter("date", date)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
