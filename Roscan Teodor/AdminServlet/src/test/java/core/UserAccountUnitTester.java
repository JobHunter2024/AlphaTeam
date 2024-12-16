package core;

import com.example.adminservlet.core.security.UserAccount;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.Assert.*;

public class UserAccountUnitTester {
    private UserAccount userAccount;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    //--------------------------------------------Interface Tests
    @Before
    public void setUp() throws Exception {
        userAccount = new UserAccount();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void testIsUserLoggedIn_NoSession() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setSession(null);

        assertFalse(userAccount.isUserLoggedIn(request));
    }

    @Test
    public void testLogout_ValidSession() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession session = new MockHttpSession();

        request.setSession(session);
        userAccount.logout(request);

        assertTrue(session.isInvalid());
    }

    @Test
    public void testLogout_NoSession() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setSession(null);

        userAccount.logout(request);
    }
}
