package core;

import com.example.adminservlet.core.extra.UUIDConverter;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class UUIDConverterUnitTester {
    private UUIDConverter uuidConverter;
    private String validString;
    private String invalidString;
    private UUID validUUID;
    private UUID invalidUUID;

    //--------------------------------------------Interface Tests
    @Before
    public void setUp() throws Exception {
        uuidConverter = new UUIDConverter();
        validUUID=UUID.randomUUID();
        invalidUUID=null;

        validString = validUUID.toString();
        invalidString = "SomeTest";
    }

    @Test
    public void convertToDatabaseColumn_ValidData() {
        String result=uuidConverter.convertToDatabaseColumn(validUUID);
        assertNotNull(result);
        assertNotNull(UUID.fromString(result));
    }

    @Test
    public void convertToDatabaseColumn_InvalidData() {
        String result=uuidConverter.convertToDatabaseColumn(invalidUUID);
        assertNull(result);
    }

    @Test
    public void convertToEntityAttribute_ValidData() {
        UUID result=uuidConverter.convertToEntityAttribute(validString);
        assertNotNull(result);
    }

    @Test
    public void convertToEntityAttribute_InvalidData() {
        assertThrows(IllegalArgumentException.class, () -> {
            uuidConverter.convertToEntityAttribute(invalidString);
        });
    }

    @Test
    public void convertToEntityAttribute_NullData() {
        UUID result=uuidConverter.convertToEntityAttribute(null);
        assertNull(result);
    }

    @Test
    public void convertToEntityAttribute_EmptyData() {
        UUID result=uuidConverter.convertToEntityAttribute("");
        assertNull(result);
    }
}
