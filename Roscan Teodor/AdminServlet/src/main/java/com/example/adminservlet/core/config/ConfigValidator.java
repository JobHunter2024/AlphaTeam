package com.example.adminservlet.core.config;

import com.example.adminservlet.core.provider.DataToExtract;
import com.example.adminservlet.core.provider.DataToExtractAdvanced;

public class ConfigValidator {
    public DataToExtract newData;

    public boolean isConfigurationValid(DataToExtract newData) {
        if(newData.getUuid()==null)
            return false;
        return true;
    }

    public boolean isConfigurationValid(DataToExtractAdvanced newData) {
        if(newData.getUuid()==null)
            return false;
        return true;
    }
}
