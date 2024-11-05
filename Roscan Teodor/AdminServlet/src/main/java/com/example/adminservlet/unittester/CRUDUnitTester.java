package com.example.adminservlet.unittester;

import com.example.adminservlet.api.configmanagement.DataToExtract;
import com.example.adminservlet.api.configmanagement.DataToExtractRepo;
import com.example.adminservlet.api.configmanagement.DatabaseCRUD;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;

import java.net.MalformedURLException;
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
        databaseCRUD = new DatabaseCRUD();

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
    public void testRead() {
        databaseCRUD.addRow(sampleData);
        DataToExtract foundData=databaseCRUD.getDataByUUID(sampleData.getUUID());
        assertEquals(foundData.getTarget(), sampleData.getTarget());
        databaseCRUD.removeRow(sampleData);
    }

    @Test
    public void testUpdate() {
        databaseCRUD.addRow(sampleData);
        databaseCRUD.updateRow(updatedData);
        DataToExtract foundData=databaseCRUD.getDataByUUID(updatedData.getUUID());
        assertNotEquals(foundData.getTarget(), sampleData.getTarget());
        assertEquals(foundData.getTarget(), updatedData.getTarget());
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
