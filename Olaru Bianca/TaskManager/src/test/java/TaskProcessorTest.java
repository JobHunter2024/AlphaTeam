
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.task.processor.DatabaseConnector;
import com.example.task.processor.TaskConfig;
import com.example.task.processor.TaskProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

public class TaskProcessorTest {

    @Mock
    private DatabaseConnector databaseConnector;

    @InjectMocks
    private TaskProcessor taskProcessor;

    private TaskConfig expectedTaskConfig;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Map<String, String> mockJsoupPath = new HashMap<>();
        mockJsoupPath.put("title", "body > div > h1");
        mockJsoupPath.put("company", "body > div > p.company");
        mockJsoupPath.put("date", "body > div > p.date");
        mockJsoupPath.put("content", "body > div > div.content");
        expectedTaskConfig = new TaskConfig(1, "mockURL.com", "pending", "scrape", mockJsoupPath);
    }

    @Test
    public void testRetrieveTask() {
        when(databaseConnector.fetchTaskConfig()).thenReturn(expectedTaskConfig);

        TaskConfig retrievedTaskConfig = taskProcessor.retrieveTaskConfig();

        assertNotNull(retrievedTaskConfig);
        assertEquals(expectedTaskConfig.getId(), retrievedTaskConfig.getId());
        assertEquals(expectedTaskConfig.getType(), retrievedTaskConfig.getType());

        verify(databaseConnector, times(1)).fetchTaskConfig();
    }
}