package com.example.adminservlet.unittester;


import com.example.adminservlet.api.configmanagement.*;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class InterfaceUnitTester {
    private Interface testInterface;
    private ConfigurationValidator configurationValidator;
    private ScrapperConfig scrapperConfig;
    private DataToExtract sampleData;
    private DataToExtract updatedData;

    //--------------------------------------------Interface Tests
    @Before
    public void setUp() throws Exception {
        configurationValidator = new ConfigurationValidator();
        scrapperConfig = new ScrapperConfig();
        testInterface = new Interface(configurationValidator, scrapperConfig);

        // Sample data for testing
        Map<String, String> samplePath = new Hashtable<>();
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
