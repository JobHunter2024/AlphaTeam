package com.example.adminservlet.core.config;

/*
 * This class uses the Facade design pattern
 * This class hides the complexity of the system showcasing only a simple interface that glues all the subsystems together
 * */

import com.example.adminservlet.core.provider.DataToExtract;
import com.example.adminservlet.core.provider.DataToExtractAdvanced;

import java.util.UUID;

public class ConfigInterface {
    private ConfigValidator configValidator;
    private ScrapperConfig scrapperConfig;

    public ConfigInterface(ConfigValidator configValidator, ScrapperConfig scrapperConfig) {
        this.configValidator = configValidator;
        this.scrapperConfig = scrapperConfig;
    }

    public void addConfiguration(DataToExtract newData) {
        if(configValidator.isConfigurationValid(newData))
            scrapperConfig.addData(newData);
    }

    public void updateConfiguration(DataToExtract newData) {
        if(configValidator.isConfigurationValid(newData))
            scrapperConfig.updateData(newData);
    }

    public void removeConfiguration(DataToExtract newData) {
        scrapperConfig.removeData(newData);
    }

    public void removeConfiguration(UUID uuid) {
        scrapperConfig.removeData(uuid);
    }

    public DataToExtract getConfigurationByUUID(UUID uuid) {
        return scrapperConfig.getDataByUUID(uuid);
    }

    public void addConfigurationAdvanced(DataToExtractAdvanced newData) {
        if(configValidator.isConfigurationValid(newData))
            scrapperConfig.addDataAdvanced(newData);
    }

    public void updateConfigurationAdvanced(DataToExtractAdvanced newData) {
        if(configValidator.isConfigurationValid(newData))
            scrapperConfig.updateDataAdvanced(newData);
    }

    public void removeConfigurationAdvanced(DataToExtractAdvanced newData) {
        scrapperConfig.removeDataAdvanced(newData);
    }

    public void removeConfigurationAdvanced(UUID uuid) {
        scrapperConfig.removeDataAdvanced(uuid);
    }

    public DataToExtractAdvanced getConfigurationByUUIDAdvanced(UUID uuid) {
        return scrapperConfig.getDataByUUIDAdvanced(uuid);
    }
}
