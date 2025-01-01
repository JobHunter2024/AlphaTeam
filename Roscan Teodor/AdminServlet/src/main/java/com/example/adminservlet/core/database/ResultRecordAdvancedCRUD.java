package com.example.adminservlet.core.database;

import com.example.adminservlet.core.provider.ResultRecordAdvanced;
import com.example.adminservlet.core.provider.ResultRecordAdvancedRepo;
import com.example.adminservlet.core.provider.ResultRecordRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/*
* This class uses the DAO (Data Access Object) design pattern
* This class separates business logic from data access, providing a clean interface for CRUD operations
* */

@Service
public class ResultRecordAdvancedCRUD {
    private ResultRecordAdvancedRepo repository;

    public ResultRecordAdvancedCRUD() {
        repository = new ResultRecordAdvancedRepo();
    }


    //CRUD
    public void listToRows(List<ResultRecordAdvanced> resultRecordList){
        for(ResultRecordAdvanced singleResultRecord : resultRecordList){
            addRow(singleResultRecord);
        }
    }

    public void addRow(ResultRecordAdvanced newData){
        repository.create(newData);
    }

    public ResultRecordAdvanced getDataByUUID(UUID uuid){
        return repository.findByUUID(uuid);
    }

    public List<ResultRecordAdvanced> getAllData(){
        return repository.findAll();
    }

    public void updateRow(ResultRecordAdvanced targetResult){
        repository.update(targetResult);
    }

    public void removeAllData(){
        repository.deleteAll();
    }

    public void removeRow(ResultRecordAdvanced targetResult){
       repository.delete(targetResult.getUuid());
    }

    public void removeRow(UUID uuid){
        repository.delete(uuid);
    }
}
