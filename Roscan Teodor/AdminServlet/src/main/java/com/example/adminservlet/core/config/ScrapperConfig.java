package com.example.adminservlet.core.config;

import com.example.adminservlet.core.data.extraction.DataToExtract;
import com.example.adminservlet.core.database.DataToExtractCRUD;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ScrapperConfig {
    List<DataToExtract> dataToExtract=new ArrayList<DataToExtract>();
    Date dateModified;
    DataToExtractCRUD dataToExtractCRUD =new DataToExtractCRUD();

    public void addData(DataToExtract newData) {
        dataToExtract.add(newData);
        dataToExtractCRUD.addRow(newData);
        dateModified = new Date();
    }

    public void removeData(DataToExtract targetData) {
        dataToExtract.remove(targetData);
        dataToExtractCRUD.removeRow(targetData);
        dateModified = new Date();
    }

    public void removeData(UUID uuid) {

       for(DataToExtract dataToExtract : dataToExtract) {
           if(dataToExtract.getUuid().equals(uuid)) {
               removeData(dataToExtract);
               break;
           }
       }

        dataToExtractCRUD.removeRow(uuid);
        dateModified = new Date();
    }

    public void updateData(DataToExtract targetData) {
        DataToExtract oldData = getDataByUUID(targetData.getUuid());
        if(oldData != null) {
            oldData.url = targetData.url;
            oldData.path = targetData.path;
            oldData.uuid = targetData.uuid;
        }
        dataToExtractCRUD.updateRow(oldData);
        dateModified = new Date();
    }

    public int getDataToExtractCount(){
        return dataToExtract.size();
    }

    public List<DataToExtract> getDataToExtract() {
        return dataToExtract;
    }

    public DataToExtract getDataByUUID(UUID uuid) {
        return dataToExtractCRUD.getDataByUUID(uuid);
    }

    public DataToExtractCRUD getDatabaseCRUD() {
        return dataToExtractCRUD;
    }
}
