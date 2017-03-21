package ee.bitweb.banklink.sdk.protocol.iPizza.response;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tobre on 21/03/2017.
 */
public class PaymentResponseTest {

    private final String transactionNumber = "123";
    private final String senderId = "sender";
    private DateTime transactionTimestamp;
    private PaymentResponse paymentResponse;

    @Before
    public void setUp() throws Exception {
        transactionTimestamp = new DateTime();
        paymentResponse = new PaymentResponse(transactionNumber, senderId, true, transactionTimestamp);
    }

    @Test
    public void testGetters() throws Exception {
        assertEquals(transactionNumber, paymentResponse.getTransactionNumber());
        assertEquals(senderId, paymentResponse.getSenderId());
        assertEquals(true, paymentResponse.isAuto());
        assertEquals(transactionTimestamp, paymentResponse.getTransactionTimestamp());

    }
}