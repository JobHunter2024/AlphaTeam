import com.example.task.processor.DatabaseConnector;
import com.example.task.processor.TaskConfig;
import com.example.task.MockFetchingService;
import com.example.task.processor.TaskProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskProcessorTest {

    private TaskProcessor taskProcessor;
    private DatabaseConnector databaseConnectorMock;
    private MockFetchingService fetchingServiceMock;

    @BeforeEach
    void setUp() {
        databaseConnectorMock = Mockito.mock(DatabaseConnector.class);
        fetchingServiceMock = Mockito.mock(MockFetchingService.class);

        taskProcessor = new TaskProcessor(databaseConnectorMock, fetchingServiceMock);
    }

    @Test
    void testRetrieveTaskConfig_Success() {
        UUID mockUUID = UUID.randomUUID();
        Map<String, String> mockJsoupPath = new HashMap<>();
        mockJsoupPath.put("title", "body > div.container > h1");
        mockJsoupPath.put("company", "body > div.container > span.company");
        mockJsoupPath.put("content", "body > div > div.content");

        TaskConfig mockTaskConfig = new TaskConfig(mockUUID, "mockURL.com", "pending", "scrape", mockJsoupPath);

        when(databaseConnectorMock.fetchTaskConfig(any(UUID.class))).thenReturn(mockTaskConfig);

        TaskConfig result = taskProcessor.retrieveTaskConfig();
        assertNotNull(result);
    }

    @Test
    void testGetResponse_Success() {
        String expectedResponse = "Mock response";
        when(fetchingServiceMock.getResponse()).thenReturn(expectedResponse);

        String response = taskProcessor.getResponse();
        assertEquals(expectedResponse, response);
    }

    @Test
    void testProcessResponse_ValidInput() {
        UUID taskId = UUID.randomUUID();
        String response = "Success response";

        boolean result = taskProcessor.processResponse(taskId, response);
        assertTrue(result);
    }

    @Test
    void testProcessResponse_NullTaskId() {
        String response = "Success response";

        boolean result = taskProcessor.processResponse(null, response);
        assertFalse(result);
    }


    @Test
    void testProcessResponse_NullResponse() {
        UUID taskId = UUID.randomUUID();

        boolean result = taskProcessor.processResponse(taskId, null);
        assertFalse(result);
    }
}