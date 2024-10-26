package com.example.adminservlet.api.configmanagement;

/*
 * This class uses the Facade design pattern
 * This class hides the complexity of the system showcasing only a simple interface that glues all the subsystems together
 * */

public class Interface {
    private ConfigurationValidator configurationValidator;
    private ScrapperConfig scrapperConfig;

    public Interface(ConfigurationValidator configurationValidator, ScrapperConfig scrapperConfig) {
        this.configurationValidator = configurationValidator;
        this.scrapperConfig = scrapperConfig;
    }

    public void addConfiguration(DataToExtract newData) {
        if(configurationValidator.isConfigurationValid(newData))
            scrapperConfig.addData(newData);
    }

    public void updateConfiguration(DataToExtract newData) {
        if(configurationValidator.isConfigurationValid(newData))
            scrapperConfig.updateData(newData);
    }

    public void removeConfiguration(DataToExtract newData) {
        scrapperConfig.removeData(newData);
    }
}
