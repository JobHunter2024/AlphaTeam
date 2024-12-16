package core;

import com.example.adminservlet.core.data.extraction.DataToExtract;
import com.example.adminservlet.core.database.DataToExtractCRUD;
import com.example.adminservlet.core.database.HistoryRecordCRUD;
import com.example.adminservlet.core.database.ResultRecordCRUD;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class DataToExtractCRUDUnitTester {

    private DataToExtract sampleData;
    private DataToExtract updatedData;
    private DataToExtractCRUD dataToExtractCRUD;
    private ResultRecordCRUD resultRecordCRUD;
    private HistoryRecordCRUD historyRecordCRUD;

    @Before
    public void setUp() throws MalformedURLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        dataToExtractCRUD = context.getBean(DataToExtractCRUD.class);
        resultRecordCRUD = context.getBean(ResultRecordCRUD.class);
        historyRecordCRUD = context.getBean(HistoryRecordCRUD.class);

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
        assertEquals(foundData.getUrl(), sampleData.getUrl());
        URL newURL = new URL(foundData.getUrl());

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
        assertNotEquals(foundData.getUrl(), sampleData.getUrl());
        assertEquals(foundData.getUrl(), updatedData.getUrl());
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

    @Test
    public void testListRows() {
        dataToExtractCRUD.addRow(sampleData);
        DataToExtract secondData = new DataToExtract("http://google.com", "div", UUID.randomUUID());
        dataToExtractCRUD.addRow(secondData);
        List<DataToExtract> foundData= dataToExtractCRUD.getAllData();
        assertNotNull(foundData);

        assertTrue(foundData.size() > 1);
    }

    @Test
    public void testListInsert() {
        DataToExtract secondData = new DataToExtract("http://google.com", "div", UUID.randomUUID());
        List<DataToExtract> inputList=new ArrayList<DataToExtract>();
        inputList.add(sampleData);
        inputList.add(secondData);

        dataToExtractCRUD.listToRows(inputList);

        for(DataToExtract data: inputList){
            DataToExtract foundData= dataToExtractCRUD.getDataByUUID(data.getUuid());
            assertNotNull(foundData);
        }
    }
}
