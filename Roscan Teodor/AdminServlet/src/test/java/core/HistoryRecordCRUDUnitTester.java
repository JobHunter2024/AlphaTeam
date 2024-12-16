package core;

import com.example.adminservlet.core.database.HistoryRecordCRUD;
import com.example.adminservlet.core.database.HistoryRecordCRUD;
import com.example.adminservlet.core.database.ResultRecordCRUD;
import com.example.adminservlet.core.provider.HistoryRecord;
import com.example.adminservlet.logger.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class HistoryRecordCRUDUnitTester {

    private HistoryRecord sampleData;
    private HistoryRecord updatedData;
    private HistoryRecordCRUD historyRecordCRUD;

    @Before
    public void setUp() throws MalformedURLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        historyRecordCRUD = context.getBean(HistoryRecordCRUD.class);

        String path="section.jobs > div.job-container > div.job-listing";
        
        sampleData = new HistoryRecord("http://google.com", path, UUID.randomUUID(), "success", "No Error");
        updatedData = new HistoryRecord("http://modified.org", path, UUID.randomUUID(), "failure", "402");
    }

    @Test
    public void testCreate() {
        historyRecordCRUD.addRow(sampleData);
        HistoryRecord foundData= historyRecordCRUD.getDataByUUID(sampleData.getUuid());
        assertNotNull(foundData);
        historyRecordCRUD.removeRow(sampleData);
    }

    @Test
    public void testRead() throws URISyntaxException, IOException {
        historyRecordCRUD.addRow(sampleData);
        HistoryRecord foundData= historyRecordCRUD.getDataByUUID(sampleData.getUuid());
        assertEquals(foundData.getUrl(), sampleData.getUrl());
        URL newURL = new URL(foundData.getUrl());

        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(newURL.toURI());
        } else {
            System.out.println("Desktop is not supported. Cannot open the browser.");
        }
        historyRecordCRUD.removeRow(sampleData);
    }

    @Test
    public void testUpdate() {
        historyRecordCRUD.addRow(sampleData);
        historyRecordCRUD.updateRow(updatedData);
        HistoryRecord foundData= historyRecordCRUD.getDataByUUID(updatedData.getUuid());
        assertNotEquals(foundData.getUrl(), sampleData.getUrl());
        assertEquals(foundData.getUrl(), updatedData.getUrl());
        historyRecordCRUD.removeRow(updatedData);
    }

    @Test
    public void testDelete() {
        historyRecordCRUD.addRow(sampleData);
        HistoryRecord foundData= historyRecordCRUD.getDataByUUID(sampleData.getUuid());
        assertNotNull(foundData);

        historyRecordCRUD.removeRow(sampleData);
        foundData= historyRecordCRUD.getDataByUUID(sampleData.getUuid());
        assertNull(foundData);
    }

    @Test
    public void testListRows() {
        historyRecordCRUD.addRow(sampleData);
        HistoryRecord secondData = new HistoryRecord("http://org.com", "div", UUID.randomUUID(), "success", "No Error");
        historyRecordCRUD.addRow(secondData);
        List<HistoryRecord> foundData= historyRecordCRUD.getAllData();
        assertNotNull(foundData);

        assertTrue(foundData.size() > 1);
    }

    @Test
    public void testListInsert() {
        HistoryRecord secondData = new HistoryRecord("http://org.com", "div", UUID.randomUUID(), "success", "No Error");
        List<HistoryRecord> inputList=new ArrayList<HistoryRecord>();
        inputList.add(sampleData);
        inputList.add(secondData);

        historyRecordCRUD.listToRows(inputList);

        for(HistoryRecord data: inputList){
            HistoryRecord foundData= historyRecordCRUD.getDataByUUID(data.getUuid());
            assertNotNull(foundData);
        }
    }
}
