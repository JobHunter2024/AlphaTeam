import com.example.task.factory.ScrapeTaskCommand;
import com.example.task.factory.TaskCommand;
import com.example.task.processor.TaskConfig;
import com.example.task.queue.TaskQueueManager;
import com.example.task.queue.TaskQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class QueueManagerTest {

    private TaskQueueManager queueManager;
    private TaskQueue taskQueue;
    private TaskConfig taskConfig;

    @BeforeEach
    void setUp() {
        taskQueue = new TaskQueue();
        queueManager = new TaskQueueManager(taskQueue);

        UUID uuid = UUID.randomUUID();
        Map<String, String> mockJsoupPath = new HashMap<>();
        mockJsoupPath.put("title", "body > div.container > h1");
        mockJsoupPath.put("company", "body > div.container > span.company");
        mockJsoupPath.put("content", "body > div > div.content");

        taskConfig = new TaskConfig(uuid, "mockURL.com", "pending", "scrape", mockJsoupPath);
    }

    @Test
    void testAddTaskToQueue() {
        TaskCommand task = new ScrapeTaskCommand(taskConfig);
        queueManager.addToQueue(task);

        assertFalse(taskQueue.isEmpty(), "Queue should not be empty");
        assertEquals(1, taskQueue.size(), "Size 1 expected");
    }

    @Test
    void testRemoveTaskFromQueue() {
        TaskCommand task = new ScrapeTaskCommand(taskConfig);
        queueManager.addToQueue(task);
        queueManager.removeFromQueue();

        assertTrue(taskQueue.isEmpty(), "Queue should be empty");
    }
}
