package ee.bitweb.banklink.sdk.protocol.iPizza.response;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tobre on 21/03/2017.
 */
public class AuthenticationResponseTest {

    private final String name = "Name";
    private final String socialSecurityNumber = "12345677";
    private final DateTime timestamp = new DateTime();
    private final String message = "message";
    private final String senderId = "sender id";
    private final String ee = "EE";
    private AuthenticationResponse authenticationResponse;

    @Before
    public void setUp() throws Exception {
        authenticationResponse = new AuthenticationResponse(name, socialSecurityNumber, timestamp, message, senderId, ee);
    }

    @Test
    public void testGetters() throws Exception {
        assertEquals(name, authenticationResponse.getName());
        assertEquals(socialSecurityNumber, authenticationResponse.getSocialSecurityNumber());
        assertEquals(timestamp, authenticationResponse.getTimestamp());
        assertEquals(message, authenticationResponse.getMessage());
        assertEquals(senderId, authenticationResponse.getSenderId());
        assertEquals(ee, authenticationResponse.getCountryCode());
    }
}