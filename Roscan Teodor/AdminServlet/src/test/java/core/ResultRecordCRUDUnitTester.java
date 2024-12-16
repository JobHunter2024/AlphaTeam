package core;

import com.example.adminservlet.core.database.ResultRecordCRUD;
import com.example.adminservlet.core.database.ResultRecordCRUD;
import com.example.adminservlet.core.provider.ResultRecord;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class ResultRecordCRUDUnitTester {

    private ResultRecord sampleData;
    private ResultRecord updatedData;
    private ResultRecordCRUD resultRecordCRUD;

    @Before
    public void setUp() throws MalformedURLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        resultRecordCRUD = context.getBean(ResultRecordCRUD.class);

        String path="section.jobs > div.job-container > div.job-listing";

        sampleData = new ResultRecord("http://google.com", new Date(), UUID.randomUUID(), "Aswesome Content");
        updatedData = new ResultRecord("http://modified.org", new Date(), UUID.randomUUID(), "Second Awesome Content");
    }

    @Test
    public void testCreate() {
        resultRecordCRUD.addRow(sampleData);
        ResultRecord foundData= resultRecordCRUD.getDataByUUID(sampleData.getUUID());
        assertNotNull(foundData);
        resultRecordCRUD.removeRow(sampleData);
    }

    @Test
    public void testRead() throws URISyntaxException, IOException {
        resultRecordCRUD.addRow(sampleData);
        ResultRecord foundData= resultRecordCRUD.getDataByUUID(sampleData.getUUID());
        assertEquals(foundData.getUrl(), sampleData.getUrl());
        URL newURL = new URL(foundData.getUrl());

        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(newURL.toURI());
        } else {
            System.out.println("Desktop is not supported. Cannot open the browser.");
        }
        resultRecordCRUD.removeRow(sampleData);
    }

    @Test
    public void testUpdate() {
        resultRecordCRUD.addRow(sampleData);
        resultRecordCRUD.updateRow(updatedData);
        ResultRecord foundData= resultRecordCRUD.getDataByUUID(updatedData.getUUID());
        assertNotEquals(foundData.getUrl(), sampleData.getUrl());
        assertEquals(foundData.getUrl(), updatedData.getUrl());
        resultRecordCRUD.removeRow(updatedData);
    }

    @Test
    public void testDelete() {
        resultRecordCRUD.addRow(sampleData);
        ResultRecord foundData= resultRecordCRUD.getDataByUUID(sampleData.getUUID());
        assertNotNull(foundData);

        resultRecordCRUD.removeRow(sampleData);
        foundData= resultRecordCRUD.getDataByUUID(sampleData.getUUID());
        assertNull(foundData);
    }

    @Test
    public void testListRows() {
        resultRecordCRUD.addRow(sampleData);
        ResultRecord secondData = new ResultRecord("http://org.com", new Date(), UUID.randomUUID(), "Third Aswesome Content");;
        resultRecordCRUD.addRow(secondData);
        List<ResultRecord> foundData= resultRecordCRUD.getAllData();
        assertNotNull(foundData);

        assertTrue(foundData.size() > 1);
    }

    @Test
    public void testListInsert() {
        ResultRecord secondData = new ResultRecord("http://org.com", new Date(), UUID.randomUUID(), "Third Aswesome Content");;
        List<ResultRecord> inputList=new ArrayList<ResultRecord>();
        inputList.add(sampleData);
        inputList.add(secondData);

        resultRecordCRUD.listToRows(inputList);

        for(ResultRecord data: inputList){
            ResultRecord foundData= resultRecordCRUD.getDataByUUID(data.getUUID());
            assertNotNull(foundData);
        }
    }
}
