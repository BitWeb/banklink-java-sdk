package ee.bitweb.banklink.sdk.banks.seb;

import ee.bitweb.banklink.sdk.Banklink;
import ee.bitweb.banklink.sdk.protocol.Protocol;
import ee.bitweb.banklink.sdk.protocol.Vendor;
import ee.bitweb.banklink.sdk.protocol.iPizza.iPizzaProtocol;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tobre on 20/03/2017.
 */
public class SebTest {

    private Protocol protocol;

    @Before
    public void setUp() throws Exception {
        protocol = new iPizzaProtocol(
                "public",
                "private",
                new Vendor("sender", "name", "account"),
                "success", "" +
                "cancel");

    }

    @Test
    public void canConstructWithProtocol() throws Exception {
        new Seb(protocol);
    }

    @Test
    public void canConstructWithProtocolAndUris() {
        new Seb(protocol, "successuri", "canceluri");
    }

    @Test
    public void canConstructWithProtocolArguments() {
        new Seb(protocol, "successuri", "canceluri", "UTF-8", "EST", "EUR");
    }
}