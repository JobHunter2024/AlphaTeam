package com.example.adminservlet.core.config;

import com.example.adminservlet.core.database.DataToExtractAdvancedCRUD;
import com.example.adminservlet.core.provider.DataToExtract;
import com.example.adminservlet.core.database.DataToExtractCRUD;
import com.example.adminservlet.core.provider.DataToExtractAdvanced;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ScrapperConfig {
    List<DataToExtract> dataToExtract = new ArrayList<>();
    List<DataToExtractAdvanced> dataToExtractAdvanced = new ArrayList<>();
    Date dateModified;
    DataToExtractCRUD dataToExtractCRUD =new DataToExtractCRUD();
    DataToExtractAdvancedCRUD dataToExtractAdvancedCRUD =new DataToExtractAdvancedCRUD();

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
        dataToExtract.remove(oldData);
        if(oldData != null) {
            oldData.url = targetData.url;
            oldData.path = targetData.path;
            oldData.uuid = targetData.uuid;
        }
        dataToExtractCRUD.updateRow(oldData);
        dataToExtract.add(targetData);
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



    // Advanced Methods for DataToExtractAdvanced
    public void addDataAdvanced(DataToExtractAdvanced newData) {
        dataToExtractAdvanced.add(newData);
        dataToExtractAdvancedCRUD.addRow(newData);
        dateModified = new Date();
    }

    public void removeDataAdvanced(DataToExtractAdvanced targetData) {
        dataToExtractAdvanced.remove(targetData);
        dataToExtractAdvancedCRUD.removeRow(targetData);
        dateModified = new Date();
    }

    public void removeDataAdvanced(UUID uuid) {
        for (DataToExtractAdvanced data : dataToExtractAdvanced) {
            if (data.getUuid().equals(uuid)) {
                removeDataAdvanced(data);
                break;
            }
        }
        dataToExtractAdvancedCRUD.removeRow(uuid);
        dateModified = new Date();
    }

    public void updateDataAdvanced(DataToExtractAdvanced targetData) {
        DataToExtractAdvanced oldData = getDataByUUIDAdvanced(targetData.getUuid());
        dataToExtractAdvanced.remove(oldData);
        if (oldData != null) {
            oldData.url = targetData.url;
            oldData.jobUrlPath = targetData.jobUrlPath;
            oldData.jobDescriptionPath=targetData.jobDescriptionPath;
            oldData.jobLocationPath=targetData.jobLocationPath;
            oldData.jobCompanyPath=targetData.jobCompanyPath;
            oldData.jobTitlePath=targetData.jobTitlePath;
            oldData.jobDatePath=targetData.jobDatePath;
            oldData.followLink=targetData.followLink;
            oldData.uuid = targetData.uuid;
        }
        dataToExtractAdvancedCRUD.updateRow(oldData);
        dataToExtractAdvanced.add(targetData);
        dateModified = new Date();
    }

    public int getDataToExtractCountAdvanced() {
        return dataToExtractAdvanced.size();
    }

    public List<DataToExtractAdvanced> getDataToExtractAdvanced() {
        return dataToExtractAdvanced;
    }

    public DataToExtractAdvanced getDataByUUIDAdvanced(UUID uuid) {
        return dataToExtractAdvancedCRUD.getDataByUUID(uuid);
    }

    public DataToExtractAdvancedCRUD getDatabaseCRUDAdvanced() {
        return dataToExtractAdvancedCRUD;
    }
}
