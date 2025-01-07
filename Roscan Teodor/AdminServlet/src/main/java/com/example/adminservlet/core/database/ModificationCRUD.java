package com.example.adminservlet.core.database;

import com.example.adminservlet.core.provider.ModificationRecord;
import com.example.adminservlet.core.provider.ModificationRepo;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

public class ModificationCRUD {
    private ModificationRepo repository;

    public ModificationCRUD() {
        repository = new ModificationRepo();
    }

    public void createModification(ModificationRecord newModificationRecord) {
        repository.create(newModificationRecord);
    }

    public List<ModificationRecord> getAllModifications() {
        return repository.findAll();
    }

    public List<ModificationRecord> getModificationBySection(String section) {
        return repository.findBySection(section);
    }

    public List<ModificationRecord> getModificationByUsername(String username) {
        return repository.findByUsername(username);
    }

    public List<ModificationRecord> getModificationByDate(Date date) {
        return repository.findByDate(date);
    }
}
