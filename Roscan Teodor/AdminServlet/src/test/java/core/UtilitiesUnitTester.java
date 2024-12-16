package core;

import com.example.adminservlet.core.extra.Utilities;
import com.example.adminservlet.core.security.UserAccount;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.json.Json;

import static org.junit.Assert.*;

public class UtilitiesUnitTester {
    private Utilities utilities;
    String password;

    //--------------------------------------------Interface Tests
    @Before
    public void setUp() throws Exception {
        utilities = new Utilities();
        password="A very strong password";
    }

    @Test
    public void passwordHash() {
        String hashedPassword=utilities.encryptorSHA256(password);
        assertNotEquals(hashedPassword,password);
    }

    @Test
    public void jsonContentGetter() {
        JSONObject json = new JSONObject();
        json.put("name", "John Doe");
        json.put("age", 30);
        json.put("email", "john.doe@example.com");

        String jsonString = utilities.jsonToString(json);

        String expectedJsonString = "{\"name\":\"John Doe\",\"age\":30,\"email\":\"john.doe@example.com\"}";

        assertEquals(expectedJsonString, jsonString);
    }
}
