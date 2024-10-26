package com.example.adminservlet.unittester;


import com.example.adminservlet.api.configmanagement.ConfigurationValidator;
import com.example.adminservlet.api.configmanagement.DataToExtract;
import com.example.adminservlet.api.configmanagement.Interface;
import com.example.adminservlet.api.configmanagement.ScrapperConfig;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnitTester {
    private Interface testInterface;
    private ConfigurationValidator configurationValidator;
    private ScrapperConfig scrapperConfig;
    private DataToExtract sampleData;
    private DataToExtract updatedData;

    @Before
    public void setUp() throws Exception {
        configurationValidator = new ConfigurationValidator();
        scrapperConfig = new ScrapperConfig();
        testInterface = new Interface(configurationValidator, scrapperConfig);

        // Sample data for testing
        Dictionary<String, String> samplePath = new Hashtable<>();
        samplePath.put("key1", "value1");
        samplePath.put("key2", "value2");

        sampleData = new DataToExtract(new URL("http://google.com"), samplePath, UUID.randomUUID());
        updatedData = new DataToExtract(new URL("http://modified.org"), samplePath, UUID.randomUUID());
        updatedData.uuid = sampleData.uuid;
    }

    @Test
    public void testAddConfiguration_ValidData() {
        testInterface.addConfiguration(sampleData);

        assertEquals(1, scrapperConfig.getDataToExtractCount());
        assertTrue(scrapperConfig.getDataToExtract().contains(sampleData));
    }

    @Test
    public void testAddConfiguration_InvalidData() {
        DataToExtract invalidData = null;
        try {
            invalidData = new DataToExtract(new URL("http://google.com"), new Hashtable<>(), null);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        testInterface.addConfiguration(invalidData);

        assertEquals(0, scrapperConfig.getDataToExtractCount());
    }

    @Test
    public void testUpdateConfiguration_ValidData() {
        // Arrange
        testInterface.addConfiguration(sampleData);

        // Act
        testInterface.updateConfiguration(updatedData);

        // Assert
        assertEquals(1, scrapperConfig.getDataToExtractCount());
        assertTrue(scrapperConfig.getDataToExtract().contains(updatedData));
    }

    @Test
    public void testUpdateConfiguration_InvalidData() {
        // Arrange
        DataToExtract invalidData = null;
        try {
            invalidData = new DataToExtract(new URL("http://google.com"), new Hashtable<>(), null);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        testInterface.addConfiguration(sampleData);
        DataToExtract sampleDataBackup=sampleData;

        // Act
        testInterface.updateConfiguration(invalidData);

        // Assert
        assertEquals(1, scrapperConfig.getDataToExtractCount());
        assertEquals(sampleData, sampleDataBackup);
    }

    @Test
    public void testRemoveConfiguration() {
        // Arrange
        testInterface.addConfiguration(sampleData);

        // Act
        testInterface.removeConfiguration(sampleData);

        // Assert
        assertEquals(0, scrapperConfig.getDataToExtractCount());
    }
}
