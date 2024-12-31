package core;

import com.example.adminservlet.core.provider.DataToExtract;
import com.example.adminservlet.core.database.DataToExtractCRUD;
import com.example.adminservlet.core.provider.HistoryRecord;
import com.example.adminservlet.core.provider.ProviderInterface;
import com.example.adminservlet.core.provider.ResultRecord;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProviderInterfaceUnitTester {
    ProviderInterface providerInterface;

    //--------------------------------------------Interface Tests
    @Before
    public void setUp() throws Exception {
        DataToExtractCRUD dataToExtract = new DataToExtractCRUD();
        providerInterface= new ProviderInterface(dataToExtract);
        providerInterface.resultMockery();
        providerInterface.historyMockery();
    }

    @Test
    public void testHistoryMocker() {
        List<HistoryRecord> foundHistory=providerInterface.historyRecordCRUD.getAllData();

        assertNotNull(foundHistory);
    }

    @Test
    public void testResultMocker() {
        List<ResultRecord> foundResults=providerInterface.resultRecordCRUD.getAllData();

        assertNotNull(foundResults);
    }

    @Test
    public void testConfigList() {
        List<DataToExtract> foundConfigs=providerInterface.getScraperConfig();

        assertNotNull(foundConfigs);
    }

    @Test
    public void testResultList() {
        List<ResultRecord> foundResults=providerInterface.getScrappingResults();

        assertNotNull(foundResults);
    }

    @Test
    public void testHistoryList() {
        List<HistoryRecord> foundHistory=providerInterface.getScrappingHistory();

        assertNotNull(foundHistory);
    }

    @Test
    public void testJson() {
        JSONObject foundJson=providerInterface.getScrappingStatistics();
        assertNull(foundJson);
    }
}
