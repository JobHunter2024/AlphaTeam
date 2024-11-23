package com.example.adminservlet.core.config;

import com.example.adminservlet.core.data.extraction.DataToExtract;
import com.example.adminservlet.core.database.DatabaseCRUD;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ScrapperConfig {
    List<DataToExtract> dataToExtract=new ArrayList<DataToExtract>();
    Date dateModified;
    DatabaseCRUD databaseCRUD=new DatabaseCRUD();

    public void addData(DataToExtract newData) {
        dataToExtract.add(newData);
        databaseCRUD.addRow(newData);
        dateModified = new Date();
    }

    public void removeData(DataToExtract targetData) {
        dataToExtract.remove(targetData);
        databaseCRUD.removeRow(targetData);
        dateModified = new Date();
    }

    public void removeData(UUID uuid) {

       for(DataToExtract dataToExtract : dataToExtract) {
           if(dataToExtract.getUUID().equals(uuid)) {
               removeData(dataToExtract);
               break;
           }
       }

        databaseCRUD.removeRow(uuid);
        dateModified = new Date();
    }

    public void updateData(DataToExtract targetData) {
        DataToExtract oldData = getDataByUUID(targetData.getUUID());
        if(oldData != null) {
            oldData.url = targetData.url;
            oldData.path = targetData.path;
            oldData.uuid = targetData.uuid;
        }
        databaseCRUD.updateRow(oldData);
        dateModified = new Date();
    }

    public int getDataToExtractCount(){
        return dataToExtract.size();
    }

    public List<DataToExtract> getDataToExtract() {
        return dataToExtract;
    }

    public DataToExtract getDataByUUID(UUID uuid) {
        return databaseCRUD.getDataByUUID(uuid);
    }

    public DatabaseCRUD getDatabaseCRUD() {
        return databaseCRUD;
    }
}
