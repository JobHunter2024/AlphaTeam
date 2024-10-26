package com.example.adminservlet.api.configmanagement;

import java.util.List;
import java.util.UUID;

/*
* This class uses the DAO (Data Access Object) design pattern
* This class separates business logic from data access, providing a clean interface for CRUD operations
* */

public class DatabaseCRUD {
    private DatabaseConnector databaseConnector;

    public void listToRows(List<DataToExtract> dataToExtract){

    }

    public void addRow(UUID rowID, DataToExtract newData){

    }

    public void removeRow(UUID rowID, DataToExtract targetData){

    }

    public void updateRow(UUID rowID, DataToExtract targetData){

    }
}
