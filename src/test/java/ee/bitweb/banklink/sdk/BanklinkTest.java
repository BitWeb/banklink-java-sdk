package ee.bitweb.banklink.sdk;

import ee.bitweb.banklink.sdk.banks.seb.Seb;
import ee.bitweb.banklink.sdk.exception.BanklinkException;
import ee.bitweb.banklink.sdk.params.PaymentRequestParams;
import ee.bitweb.banklink.sdk.protocol.Protocol;
import ee.bitweb.banklink.sdk.protocol.Vendor;
import ee.bitweb.banklink.sdk.protocol.iPizza.iPizzaProtocol;
import ee.bitweb.banklink.sdk.protocol.iPizza.request.PaymentRequest;
import ee.bitweb.banklink.sdk.protocol.iPizza.response.PaymentResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tobre on 20/03/2017.
 */
public class BanklinkTest {

    public static final String PRIVATE_KEY = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpQIBAAKCAQEAtqbq6KAn92LV5s3wDQUmZAiudW/VACEefr+zABAXpJFp+wMF\n" +
            "gkUK6HJV1vn8worJiKtppVmFOc/ASbLcAo/eT4uIE9xg6PRPvp55+56hl6qzgBXr\n" +
            "lEZwyRhZcdiC3qQBpxlBn2cLLGWyzrPQnxc3vlMM+N2hgziJunozRBaLhLj6LG/0\n" +
            "2gxJsYzlFlr0HLwW2X3xxhIJZtt1KUQ1UYybm6h615Zu+s66oDJip0mCUW8iiBsr\n" +
            "ofvFR6Ot11y60dFsWpPf4X4g8uL+jmubDlY4NEHmOtNoNwLCcMz+jIpdlNz+Ymmd\n" +
            "tK366AuhAQzTa7+jOISyTqhw/w7ph4TrIJTcqQIDAQABAoIBABprur87q4xVd6uT\n" +
            "To4TmKIPv2ytaJpEMC0VfcccjM75WeM9ZKabZiCJqXEXaECb4tsqMHYCpQrfymje\n" +
            "voSAWyAWhvCYiEwqSv6/3dVP9QADaUO16AXiyp3ML+QP9Z5PCw4o0R1FwpABhgPw\n" +
            "btUUfrPBP7cgGCTrIMEgvBm1SSlBeg50+kAi/tZ02EwAadlpK9wbnfRbEhtlTMiD\n" +
            "HW05Tq5TykyWSe/A5CvWlXJCzWFRkQqokgnYn+xWSYuM4Vd0LRjmUD5a3063h2qk\n" +
            "Up+qovtjAf8edVWAxpVRzfLjoFbuTovmw/rLPEvvIu3hjW8Z0YK9/sYHb5sWcHCA\n" +
            "mkacmiECgYEA4nyB76hvPGDymj8RtE0p86tMPBxIVJMetrwS7oZHN8c29sg4SpGF\n" +
            "LMURsjC7JTMChlc1yzg5KFSpjy3ubVKA62/IWy5a+Lh2qzZ757dAh+4G0X7gqitF\n" +
            "0ka833bsR98wBpZ76icVI+dVDTqdQbYyIlgL3J/3s1dtBNWS7pcbuvMCgYEAznQd\n" +
            "UAT4B6ilILEHasCVPFBXdbfNa6IQxrHk2zM3rKSiG0YCJtcsEUr1cqeWzHshlSnw\n" +
            "e8i+Pp/aNDcxCHcsFkaEaZHIs748M0/XlB+jVc9AHtSQU6Z4IQoUWpVAPSy/LtNY\n" +
            "pCwv3zwHnt+XOtiSBgvoe7QjvBMN0KHuPSb9+PMCgYEAwYqsbuR8yhf+uRkURAN8\n" +
            "yiGj7+orVTqzkGx/sR1GVsAClDjdAbwBFhUB3kgOZOeGTM7RxWuyTGiszRHvIbTT\n" +
            "Pl4z1Nd0Hf9kSUMx9v1+Q6qAYR4PVtvLurmARWYW3AQ5widulNX5FslkwP1JzRGp\n" +
            "Hfgnis2YmsUnAG6SNRkkNUkCgYEAl6S+aypbLPT45Xxi4Ey40WaJ91OctXSDMg80\n" +
            "9LU4Lb13vOS9a94c5k7S3vauU2AWGMSHecTHyKQN2NC5WWh/n5UIaarFaxFOkroQ\n" +
            "tSJQDRWAGaU0pdze3BXVQXvjhYu6miBvOZejSiuDxXSiVn0BNSPGDicRIHI2cYqf\n" +
            "vZdIcXUCgYEAv5y++kzj0p4zFNUTuPdv1H0HftThd2vAc4I40CJFh99tQzF9Zo+n\n" +
            "dUOwcla6G7jL64e5Wo2XMGyTgh496VsYcB+tc6P8LciM0qjpQgjkoWSULzDlkvSI\n" +
            "B12xir20Ely9x7/f4QEk8DPoAwzSVxqslQwk4lScEN+z7Ag/pGasUhw=\n" +
            "-----END RSA PRIVATE KEY-----";

    Protocol protocol;
    private Banklink banklink;

    @Before
    public void setUp() throws Exception {
        protocol = new iPizzaProtocol(
                "public",
                PRIVATE_KEY,
                new Vendor("sender", "name", "account"),
                "success", "" +
                "cancel");

        banklink = new Seb(protocol);
    }

    @Test(expected = BanklinkException.class)
    public void throwExceptionWhenUsingBadlyFormattedPrivateKey() throws Exception {
        Protocol protocol = new iPizzaProtocol(
                "public",
                "private",
                new Vendor("sender", "name", "account"),
                "success", "" +
                "cancel");

        Banklink banklink = new Seb(protocol);
        banklink.prepareRequest(new PaymentRequestParams(
                "1",
                2.0,
                "Message",
                "1234"
        ));
    }

    @Test
    public void testPrepareRequest() throws Exception {
        PaymentRequest paymentRequest = banklink.prepareRequest(new PaymentRequestParams(
                "1",
                2.0,
                "Message",
                "1234"
        ));
       // assertEquals("expected", paymentRequest.getRequestUri());

    }


}