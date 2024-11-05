package com.example.adminservlet.api.configmanagement;

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

    public void updateData(DataToExtract targetData) {
        DataToExtract oldData = getDataByUUID(targetData.getUUID());
        if(oldData != null) {
            oldData.url = targetData.url;
            oldData.path = targetData.path;
            oldData.uuid = targetData.uuid;
        }
        dateModified = new Date();
    }

    public int getDataToExtractCount(){
        return dataToExtract.size();
    }

    public List<DataToExtract> getDataToExtract() {
        return dataToExtract;
    }

    public DataToExtract getDataByUUID(UUID uuid) {
        for (DataToExtract data : dataToExtract) {
            if (data != null && uuid.equals(data.uuid)) {
                return data;
            }
        }
        return null;
    }
}
