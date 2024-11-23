package com.example.adminservlet.core.database;

import com.example.adminservlet.core.data.extraction.DataToExtract;
import com.example.adminservlet.core.data.extraction.DataToExtractRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

/*
* This class uses the DAO (Data Access Object) design pattern
* This class separates business logic from data access, providing a clean interface for CRUD operations
* */

@Service
public class DatabaseCRUD {
    private DataToExtractRepo repository;

    public DatabaseCRUD() {
        repository = new DataToExtractRepo();
    }

    public void listToRows(List<DataToExtract> dataToExtract){
        for(DataToExtract singleDataToExtract : dataToExtract){
            addRow(singleDataToExtract);
        }
    }

    public void addRow(DataToExtract newData){
        repository.create(newData);
    }

    public void removeRow(DataToExtract targetData){
       repository.delete(targetData.getUUID());
    }

    public void removeRow(UUID uuid){
        repository.delete(uuid);
    }

    public void updateRow(DataToExtract targetData){
        repository.update(targetData);
    }

    public DataToExtract getDataByUUID(UUID uuid){
        return repository.findByUUID(uuid);
    }

    public List<DataToExtract> getAllData(){
        return repository.findAll();
    }
}

/*  SCRIPT FOR TABLE CREATION
-- Table for DataToExtract entity
CREATE TABLE DataToExtract (
    id SERIAL PRIMARY KEY,                     -- Auto-generated primary key
    url TEXT NOT NULL,                         -- URL stored as TEXT
    uuid BYTEA NOT NULL UNIQUE                 -- UUID stored as BYTEA, ensuring uniqueness
);

-- Table for the Path element collection associated with DataToExtract
CREATE TABLE Path (
    parentid BIGINT NOT NULL,                  -- Foreign key referencing DataToExtract
    key TEXT NOT NULL,                         -- Key for the map entry, stored as TEXT
    value TEXT,                                -- Value for the map entry, stored as TEXT
    PRIMARY KEY (parentid, key),               -- Composite primary key for uniqueness within each parent
    FOREIGN KEY (parentid) REFERENCES DataToExtract(id) ON DELETE CASCADE -- Ensures referential integrity
);
 */
