package com.example.adminservlet.unittester;

import com.example.adminservlet.api.configmanagement.DataToExtract;
import com.example.adminservlet.api.configmanagement.DataToExtractRepo;
import com.example.adminservlet.api.configmanagement.DatabaseCRUD;
import com.example.adminservlet.logger.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.Desktop;

import javax.persistence.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;

public class CRUDUnitTester {

    private DataToExtract sampleData;
    private DataToExtract updatedData;
    private DatabaseCRUD databaseCRUD;

    @Before
    public void setUp() throws MalformedURLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        databaseCRUD = context.getBean(DatabaseCRUD.class);

        // Sample data for testing
        Map<String, String> samplePath = new Hashtable<>();
        samplePath.put("key1", "value1");
        samplePath.put("key2", "value2");

        sampleData = new DataToExtract(new URL("http://google.com"), samplePath, UUID.randomUUID());
        updatedData = new DataToExtract(new URL("http://modified.org"), samplePath, UUID.randomUUID());
    }

    @Test
    public void testCreate() {
        databaseCRUD.addRow(sampleData);
        DataToExtract foundData=databaseCRUD.getDataByUUID(sampleData.getUUID());
        assertNotNull(foundData);
        databaseCRUD.removeRow(sampleData);
    }

    @Test
    public void testRead() throws URISyntaxException, IOException {
        databaseCRUD.addRow(sampleData);
        DataToExtract foundData=databaseCRUD.getDataByUUID(sampleData.getUUID());
        assertEquals(foundData.getUrl(), sampleData.getUrl());
        URL newURL = foundData.getUrl();

        // Open the URL in the default web browser
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(newURL.toURI());
        } else {
            System.out.println("Desktop is not supported. Cannot open the browser.");
        }
        databaseCRUD.removeRow(sampleData);
    }

    @Test
    public void testUpdate() {
        databaseCRUD.addRow(sampleData);
        databaseCRUD.updateRow(updatedData);
        DataToExtract foundData=databaseCRUD.getDataByUUID(updatedData.getUUID());
        assertNotEquals(foundData.getUrl(), sampleData.getUrl());
        assertEquals(foundData.getUrl(), updatedData.getUrl());
        databaseCRUD.removeRow(updatedData);
    }

    @Test
    public void testDelete() {
        databaseCRUD.addRow(sampleData);
        DataToExtract foundData=databaseCRUD.getDataByUUID(sampleData.getUUID());
        assertNotNull(foundData);

        databaseCRUD.removeRow(sampleData);
        foundData=databaseCRUD.getDataByUUID(sampleData.getUUID());
        assertNull(foundData);
    }
}
