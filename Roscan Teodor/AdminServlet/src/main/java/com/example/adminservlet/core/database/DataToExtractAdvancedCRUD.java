package com.example.adminservlet.core.database;

import com.example.adminservlet.core.provider.DataToExtractAdvanced;
import com.example.adminservlet.core.provider.DataToExtractAdvancedRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/*
* This class uses the DAO (Data Access Object) design pattern
* This class separates business logic from data access, providing a clean interface for CRUD operations
* */

@Service
public class DataToExtractAdvancedCRUD {
    private DataToExtractAdvancedRepo repository;

    public DataToExtractAdvancedCRUD() {
        repository = new DataToExtractAdvancedRepo();
    }


    //CRUD
    public void listToRows(List<DataToExtractAdvanced> dataToExtractAdvanced){
        for(DataToExtractAdvanced singleDataToExtractAdvanced : dataToExtractAdvanced){
            addRow(singleDataToExtractAdvanced);
        }
    }

    public void addRow(DataToExtractAdvanced newData){
        repository.create(newData);
    }

    public DataToExtractAdvanced getDataByUUID(UUID uuid){
        return repository.findByUUID(uuid);
    }

    public List<DataToExtractAdvanced> getAllData(){
        return repository.findAll();
    }

    public void updateRow(DataToExtractAdvanced targetData){
        repository.update(targetData);
    }

    public void removeRow(DataToExtractAdvanced targetData){
       repository.delete(targetData.getUuid());
    }

    public void removeRow(UUID uuid){
        repository.delete(uuid);
    }
}
