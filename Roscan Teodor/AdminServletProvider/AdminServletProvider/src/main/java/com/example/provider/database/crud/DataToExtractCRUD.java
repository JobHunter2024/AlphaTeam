package com.example.provider.database.crud;


import com.example.provider.database.entity.DataToExtract;
import com.example.provider.database.repo.DataToExtractRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/*
* This class uses the DAO (Data Access Object) design pattern
* This class separates business logic from data access, providing a clean interface for CRUD operations
* */

@Service
public class DataToExtractCRUD {
    private DataToExtractRepo repository;

    public DataToExtractCRUD() {
        repository = new DataToExtractRepo();
    }


    //CRUD
    public void listToRows(List<DataToExtract> dataToExtract){
        for(DataToExtract singleDataToExtract : dataToExtract){
            addRow(singleDataToExtract);
        }
    }

    public void addRow(DataToExtract newData){
        repository.create(newData);
    }

    public DataToExtract getDataByUUID(UUID uuid){
        return repository.findByUUID(uuid);
    }

    public List<DataToExtract> getAllData(){
        return repository.findAll();
    }

    public void updateRow(DataToExtract targetData){
        repository.update(targetData);
    }

    public void removeRow(DataToExtract targetData){
       repository.delete(targetData.getUuid());
    }

    public void removeRow(UUID uuid){
        repository.delete(uuid);
    }
}
