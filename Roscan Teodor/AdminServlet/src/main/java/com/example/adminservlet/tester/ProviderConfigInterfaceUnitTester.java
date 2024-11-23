package com.example.adminservlet.tester;


import com.example.adminservlet.core.config.*;
import com.example.adminservlet.core.data.extraction.DataToExtract;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class ProviderConfigInterfaceUnitTester {
    private ConfigInterface testConfigInterface;
    private ConfigValidator configValidator;
    private ScrapperConfig scrapperConfig;
    private DataToExtract sampleData;
    private DataToExtract updatedData;

    //--------------------------------------------Interface Tests
    @Before
    public void setUp() throws Exception {
        configValidator = new ConfigValidator();
        scrapperConfig = new ScrapperConfig();
        testConfigInterface = new ConfigInterface(configValidator, scrapperConfig);

        // Sample data for testing
        Map<String, String> samplePath = new Hashtable<>();
        samplePath.put("key1", "value1");
        samplePath.put("key2", "value2");

        sampleData = new DataToExtract("http://google.com", samplePath, UUID.randomUUID());
        updatedData = new DataToExtract("http://modified.org", samplePath, UUID.randomUUID());
        updatedData.uuid = sampleData.uuid;
    }

    @Test
    public void testAddConfiguration_ValidData() {
        testConfigInterface.addConfiguration(sampleData);

        assertEquals(1, scrapperConfig.getDataToExtractCount());
        assertTrue(scrapperConfig.getDataToExtract().contains(sampleData));
    }

    @Test
    public void testAddConfiguration_InvalidData() {
        DataToExtract invalidData = null;
        invalidData = new DataToExtract("http://google.com", new Hashtable<>(), null);

        testConfigInterface.addConfiguration(invalidData);

        assertEquals(0, scrapperConfig.getDataToExtractCount());
    }

    @Test
    public void testUpdateConfiguration_ValidData() {
        // Arrange
        testConfigInterface.addConfiguration(sampleData);

        // Act
        testConfigInterface.updateConfiguration(updatedData);

        // Assert
        assertEquals(1, scrapperConfig.getDataToExtractCount());
        assertTrue(scrapperConfig.getDataToExtract().contains(updatedData));
    }

    @Test
    public void testUpdateConfiguration_InvalidData() {
        // Arrange
        DataToExtract invalidData = null;
        invalidData = new DataToExtract("http://google.com", new Hashtable<>(), null);
        testConfigInterface.addConfiguration(sampleData);
        DataToExtract sampleDataBackup=sampleData;

        // Act
        testConfigInterface.updateConfiguration(invalidData);

        // Assert
        assertEquals(1, scrapperConfig.getDataToExtractCount());
        assertEquals(sampleData, sampleDataBackup);
    }

    @Test
    public void testRemoveConfiguration() {
        // Arrange
        testConfigInterface.addConfiguration(sampleData);

        // Act
        testConfigInterface.removeConfiguration(sampleData);

        // Assert
        assertEquals(0, scrapperConfig.getDataToExtractCount());
    }

}
