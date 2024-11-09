
import com.example.task.aspects.LoggingAspect;
import com.example.task.processor.LoggingAPIClient;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoggingAspectTest {

    @Test
    public void testLogFetchingResponse() {
        LoggingAPIClient loggingAPIClient = mock(LoggingAPIClient.class);
        LoggingAspect loggingAspect = new LoggingAspect(loggingAPIClient);

        String response = "Processed response";

        loggingAspect.logFetchingResponse(response);

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> statusCaptor = ArgumentCaptor.forClass(String.class);

        verify(loggingAPIClient, times(1)).sendLog(messageCaptor.capture(), statusCaptor.capture());

        assertEquals("Fetching response processed: " + response, messageCaptor.getValue());
        assertEquals("SUCCESS", statusCaptor.getValue());
    }
}