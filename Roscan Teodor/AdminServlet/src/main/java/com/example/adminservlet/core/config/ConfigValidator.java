package com.example.adminservlet.core.config;

import com.example.adminservlet.core.data.extraction.DataToExtract;

public class ConfigValidator {
    public DataToExtract newData;

    public boolean isConfigurationValid(DataToExtract newData) {
        if(newData.getUuid()==null)
            return false;
        return true;
    }
}
