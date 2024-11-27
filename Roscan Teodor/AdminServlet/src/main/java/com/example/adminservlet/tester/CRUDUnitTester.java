package com.example.adminservlet.tester;

import com.example.adminservlet.core.data.extraction.DataToExtract;
import com.example.adminservlet.core.database.DataToExtractCRUD;
import com.example.adminservlet.logger.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.Desktop;

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
    private DataToExtractCRUD dataToExtractCRUD;

    @Before
    public void setUp() throws MalformedURLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        dataToExtractCRUD = context.getBean(DataToExtractCRUD.class);

        String path="section.jobs > div.job-container > div.job-listing";

        sampleData = new DataToExtract("http://google.com", path, UUID.randomUUID());
        updatedData = new DataToExtract("http://modified.org", path, UUID.randomUUID());
    }

    @Test
    public void testCreate() {
        dataToExtractCRUD.addRow(sampleData);
        DataToExtract foundData= dataToExtractCRUD.getDataByUUID(sampleData.getUuid());
        assertNotNull(foundData);
        dataToExtractCRUD.removeRow(sampleData);
    }

    @Test
    public void testRead() throws URISyntaxException, IOException {
        dataToExtractCRUD.addRow(sampleData);
        DataToExtract foundData= dataToExtractCRUD.getDataByUUID(sampleData.getUuid());
        assertEquals(foundData.getUrlString(), sampleData.getUrlString());
        URL newURL = new URL(foundData.getUrlString());

        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(newURL.toURI());
        } else {
            System.out.println("Desktop is not supported. Cannot open the browser.");
        }
        dataToExtractCRUD.removeRow(sampleData);
    }

    @Test
    public void testUpdate() {
        dataToExtractCRUD.addRow(sampleData);
        dataToExtractCRUD.updateRow(updatedData);
        DataToExtract foundData= dataToExtractCRUD.getDataByUUID(updatedData.getUuid());
        assertNotEquals(foundData.getUrlString(), sampleData.getUrlString());
        assertEquals(foundData.getUrlString(), updatedData.getUrlString());
        dataToExtractCRUD.removeRow(updatedData);
    }

    @Test
    public void testDelete() {
        dataToExtractCRUD.addRow(sampleData);
        DataToExtract foundData= dataToExtractCRUD.getDataByUUID(sampleData.getUuid());
        assertNotNull(foundData);

        dataToExtractCRUD.removeRow(sampleData);
        foundData= dataToExtractCRUD.getDataByUUID(sampleData.getUuid());
        assertNull(foundData);
    }
}
