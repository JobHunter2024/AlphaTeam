package com.example.provider.database.crud;


import com.example.provider.database.entity.ResultRecord;
import com.example.provider.database.repo.ResultRecordRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/*
* This class uses the DAO (Data Access Object) design pattern
* This class separates business logic from data access, providing a clean interface for CRUD operations
* */

@Service
public class ResultRecordCRUD {
    private ResultRecordRepo repository;

    public ResultRecordCRUD() {
        repository = new ResultRecordRepo();
    }


    //CRUD
    public void listToRows(List<ResultRecord> dataToExtract){
        for(ResultRecord singleResultRecord : dataToExtract){
            addRow(singleResultRecord);
        }
    }

    public void addRow(ResultRecord newData){
        repository.create(newData);
    }

    public ResultRecord getDataByUUID(UUID uuid){
        return repository.findByUUID(uuid);
    }

    public List<ResultRecord> getAllData(){
        return repository.findAll();
    }

    public void updateRow(ResultRecord targetData){
        repository.update(targetData);
    }

    public void removeRow(ResultRecord targetData){
       repository.delete(targetData.getUUID());
    }

    public void removeRow(UUID uuid){
        repository.delete(uuid);
    }
}
