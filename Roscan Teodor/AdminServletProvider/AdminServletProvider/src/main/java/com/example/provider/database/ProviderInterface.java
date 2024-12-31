package com.example.provider.database;

import com.example.provider.database.crud.DataToExtractCRUD;
import com.example.provider.database.crud.HistoryRecordCRUD;
import com.example.provider.database.crud.ResultRecordCRUD;
import com.example.provider.database.entity.DataToExtract;
import com.example.provider.database.entity.HistoryRecord;
import com.example.provider.database.entity.ResultRecord;
import org.json.JSONObject;

import java.util.List;

public class ProviderInterface {
    public DataToExtractCRUD dataToExtractCRUD;
    public HistoryRecordCRUD historyRecordCRUD;
    public ResultRecordCRUD resultRecordCRUD;

    public ProviderInterface() {

    }

    public ProviderInterface(DataToExtractCRUD dataToExtractCRUD) {
        this.dataToExtractCRUD = dataToExtractCRUD;
        this.historyRecordCRUD = new HistoryRecordCRUD();
        this.resultRecordCRUD = new ResultRecordCRUD();
    }

    public List<HistoryRecord> getScrappingHistory(){
        return historyRecordCRUD.getAllData();
    }

    public List<ResultRecord> getScrappingResults(){
        return resultRecordCRUD.getAllData();
    }

    public List<DataToExtract> getScraperConfig()
    {
        return dataToExtractCRUD.getAllData();
    }

    public JSONObject getScrappingStatistics(){
        return null;
    }
}
