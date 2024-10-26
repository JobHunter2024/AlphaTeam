package com.example.adminservlet.api.configmanagement;

public class ConfigurationValidator {
    public DataToExtract newData;

    public boolean isConfigurationValid(DataToExtract newData) {
        if(newData.getUUID()==null)
            return false;
        return true;
    }
}
