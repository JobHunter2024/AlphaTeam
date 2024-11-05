package com.example.adminservlet.api.configmanagement;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

/*
* This class uses the DAO (Data Access Object) design pattern
* This class separates business logic from data access, providing a clean interface for CRUD operations
* */

public class DatabaseCRUD {
    private DatabaseConnector databaseConnector;
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

    public void updateRow(DataToExtract targetData){
        repository.update(targetData);
    }

    public DataToExtract getDataByUUID(UUID uuid){
        return repository.findByUUID(uuid);
    }
}
