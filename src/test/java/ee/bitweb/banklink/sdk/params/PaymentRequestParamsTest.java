package ee.bitweb.banklink.sdk.params;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tobre on 21/03/2017.
 */
public class PaymentRequestParamsTest {

    @Test
    public void canConstruct() throws Exception {
        new PaymentRequestParams("1", 2.2, "message", "123");
        new PaymentRequestParams("1", 2.2, "message", "123", "EST", "EUR");
        new PaymentRequestParams("1", 2.2, "message", "123", "EST", "EUR", "http://localhost/success", "http://localhost/cancel");
    }
}