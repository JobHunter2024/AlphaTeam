package com.example.provider.database.crud;

import com.example.provider.database.entity.HistoryRecord;
import com.example.provider.database.repo.HistoryRecordRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/*
* This class uses the DAO (Data Access Object) design pattern
* This class separates business logic from data access, providing a clean interface for CRUD operations
* */

@Service
public class HistoryRecordCRUD {
    private HistoryRecordRepo repository;

    public HistoryRecordCRUD() {
        repository = new HistoryRecordRepo();
    }


    //CRUD
    public void listToRows(List<HistoryRecord> dataToExtract){
        for(HistoryRecord singleHistoryRecord : dataToExtract){
            addRow(singleHistoryRecord);
        }
    }

    public void addRow(HistoryRecord newData){
        repository.create(newData);
    }

    public HistoryRecord getDataByUUID(UUID uuid){
        return repository.findByUUID(uuid);
    }

    public List<HistoryRecord> getAllData(){
        return repository.findAll();
    }

    public void updateRow(HistoryRecord targetData){
        repository.update(targetData);
    }

    public void removeRow(HistoryRecord targetData){
       repository.delete(targetData.getUuid());
    }

    public void removeRow(UUID uuid){
        repository.delete(uuid);
    }
}
