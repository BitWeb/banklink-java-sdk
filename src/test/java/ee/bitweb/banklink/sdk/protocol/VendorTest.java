package ee.bitweb.banklink.sdk.protocol;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tobre on 21/03/2017.
 */
public class VendorTest {

    private Vendor vendor;

    @Before
    public void setUp() throws Exception {
        vendor = new Vendor("senderid", "sender name", "12356");
    }

    @Test
    public void testGetters() throws Exception {
        assertEquals("senderid", vendor.getSenderId());
        assertEquals("sender name", vendor.getName());
        assertEquals("12356", vendor.getAccountNumber());
    }
}