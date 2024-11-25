package com.example.adminservlet.core.database;

import com.example.adminservlet.core.provider.HistoryRecord;
import com.example.adminservlet.core.provider.HistoryRecordRepo;
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


    public void listToRows(List<HistoryRecord> dataToExtract){
        for(HistoryRecord singleHistoryRecord : dataToExtract){
            addRow(singleHistoryRecord);
        }
    }

    public void addRow(HistoryRecord newData){
        repository.create(newData);
    }

    public void removeRow(HistoryRecord targetData){
       repository.delete(targetData.getUUID());
    }

    public void removeRow(UUID uuid){
        repository.delete(uuid);
    }

    public void updateRow(HistoryRecord targetData){
        repository.update(targetData);
    }

    public HistoryRecord getDataByUUID(UUID uuid){
        return repository.findByUUID(uuid);
    }

    public List<HistoryRecord> getAllData(){
        return repository.findAll();
    }
}
