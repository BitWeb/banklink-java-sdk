package ee.bitweb.banklink.sdk.banks.lhv;

import ee.bitweb.banklink.sdk.banks.seb.Seb;
import ee.bitweb.banklink.sdk.protocol.Protocol;
import ee.bitweb.banklink.sdk.protocol.Vendor;
import ee.bitweb.banklink.sdk.protocol.iPizza.iPizzaProtocol;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by tobre on 20/03/2017.
 */
public class LhvTest {

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
        new Lhv(protocol);
    }

    @Test
    public void canConstructWithProtocolAndUris() {
        new Lhv(protocol, "successuri", "canceluri");
    }

    @Test
    public void canConstructWithProtocolArguments() {
        new Lhv(protocol, "successuri", "canceluri", "UTF-8", "EST", "EUR");
    }
}