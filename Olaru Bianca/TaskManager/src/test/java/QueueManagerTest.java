import com.example.task.factory.ScrapeTaskCommand;
import com.example.task.factory.TaskCommand;
import com.example.task.queue.TaskQueueManager;
import com.example.task.queue.TaskQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueManagerTest {

    private TaskQueueManager queueManager;
    private TaskQueue taskQueue;

    @BeforeEach
    void setUp() {
        queueManager = new TaskQueueManager();
        taskQueue = new TaskQueue();
    }

    @Test
    void testAddTaskToQueue() {
        TaskCommand task = new ScrapeTaskCommand();
        queueManager.addToQueue(task);

        assertFalse(taskQueue.isEmpty(), "Queue should not be empty");
        assertEquals(1, taskQueue.size(), "Size 1 expected");
    }

    @Test
    void testRemoveTaskFromQueue() {
        TaskCommand task = new ScrapeTaskCommand();
        queueManager.addToQueue(task);
        queueManager.removeFromQueue(task);

        assertTrue(taskQueue.isEmpty(), "Queue should be empty");
    }
}
