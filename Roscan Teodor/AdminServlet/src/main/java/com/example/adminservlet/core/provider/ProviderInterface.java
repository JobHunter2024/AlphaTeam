package com.example.adminservlet.core.provider;

import com.example.adminservlet.core.data.extraction.DataToExtract;
import com.example.adminservlet.core.database.DatabaseCRUD;
import org.json.JSONObject;

import java.util.List;

public class ProviderInterface {
    DatabaseCRUD databaseCRUD;

    public ProviderInterface() {

    }

    public ProviderInterface(DatabaseCRUD databaseCRUD) {
        this.databaseCRUD = databaseCRUD;
    }

    public JSONObject getScrappingHistory(){

        return null;
    }

    public JSONObject getScrappingResults(){

        return null;
    }

    public JSONObject getScrappingStatistics(){

        return null;
    }

    public List<DataToExtract> getScraperConfig()
    {
        return databaseCRUD.getAllData();
    }
}
