package ee.bitweb.banklink.sdk;

import ee.bitweb.banklink.sdk.banks.seb.Seb;
import ee.bitweb.banklink.sdk.exception.BanklinkException;
import ee.bitweb.banklink.sdk.params.AuthenticationRequestParams;
import ee.bitweb.banklink.sdk.params.PaymentRequestParams;
import ee.bitweb.banklink.sdk.protocol.Protocol;
import ee.bitweb.banklink.sdk.protocol.Vendor;
import ee.bitweb.banklink.sdk.protocol.iPizza.Fields;
import ee.bitweb.banklink.sdk.protocol.iPizza.Mac;
import ee.bitweb.banklink.sdk.protocol.iPizza.iPizzaProtocol;
import ee.bitweb.banklink.sdk.protocol.iPizza.request.AuthenticationRequest;
import ee.bitweb.banklink.sdk.protocol.iPizza.request.PaymentRequest;
import ee.bitweb.banklink.sdk.protocol.iPizza.response.AuthenticationResponse;
import ee.bitweb.banklink.sdk.protocol.iPizza.response.PaymentResponse;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by tobre on 20/03/2017.
 */
public class BanklinkTest extends BankLinkTestStub {


    Protocol protocol;
    private Banklink banklink;

    @Before
    public void setUp() throws Exception {

        PublicKey publicKey = Mac.getPublicKeyFromCertificateString(PUBLIC_KEY);
        PrivateKey privateKey = Mac.getPrivateKey(PRIVATE_KEY);

        protocol = new iPizzaProtocol(
                publicKey,
                privateKey,
                new Vendor("sender", "name", "account"));

        protocol.setTestMode(true);
        banklink = new Seb(protocol);
    }
/*
    @Test
    public void testMakePaymentRequest() throws Exception {
        PaymentRequest paymentRequest = banklink.prepareRequest(new PaymentRequestParams(
                "1",
                2.0,
                "Message",
                "1234",
                "success",
                "cancel",
                "EUR",
                "EST1",
                "UTF-8"
        ));

        String htmlForm = paymentRequest.createRequestForm();
        assertEquals(true, htmlForm.contains("Message"));
        assertEquals(true, htmlForm.contains("008"));
        assertEquals(true, htmlForm.contains("UTF-8"));
        assertEquals(true, htmlForm.contains("1012"));
        assertEquals(true, htmlForm.contains("success"));
        assertEquals(true, htmlForm.contains("EST1"));
        assertEquals(true, htmlForm.contains("cancel"));
        assertEquals(true, htmlForm.contains(paymentRequest.getRequestData().get(Fields.DATETIME)));
        assertEquals(true, htmlForm.contains(paymentRequest.getRequestData().get(Fields.MAC)));
        assertEquals(true, htmlForm.contains("sender"));
        assertEquals(true, htmlForm.contains("1234"));
        assertEquals(true, htmlForm.contains("EUR"));

        PaymentResponse response = (PaymentResponse) banklink.handleResponse(new HashMap<String, String>() {{
            put("VK_STAMP", "2");
            put("VK_AUTO", "Y");
            put("VK_T_NO", "1");
            put("VK_VERSION", "008");
            put("VK_SND_ID", "HP");
            put("VK_AMOUNT", "0.01");
            put("VK_T_DATETIME", "2017-03-20T15:41:54+0000");
            put("VK_CURR", "EUR");
            put("VK_SND_ACC", "EE152200221234567897");
            put("VK_SERVICE", "1111");
            put("VK_REC_ACC", "");
            put("VK_MSG", "BitWeb test");
            put("VK_SND_NAME", "Tõõger Leõpäöld");
            put("VK_REC_ID", "uid55");
            put("VK_REF", "123");
            put("VK_MAC", "EUVjgdwDCO4+qeDVseYKCKRH9YOfUocElstdcai8ifWX3eOCtr98ipheHmjH1lT4ci/DJ9m1Kil72ef6SikrVj5TthL4qRNKLm1qmwAkgvCIaOBgkhs1mZKHa1XeTmV+2OBnXfcSBOpaXnDGwyLZGNa/wTdmaLeerHpyknYu0xCwyJaaES+upuMaqITROU1UHHohS94qsXQbo7ik5fdcYKHNIsjn0MrwOKsUFbm9u1bfaVkUA09o2iZBobUaLocoUxeMH/F1qRmdJEDDpLzD8i0WBgzgaZnT+UStAcGCl7STkjIv1cD1+sQVa5eRmL0IAV92EeGbcOt+E7TAXAKFNQ==");
            put("VK_REC_NAME", "");
            put("VK_LANG", "EST");
            put("VK_ENCODING", "UTF-8");
        }});

        assertEquals("HP", response.getSenderId());
        assertEquals("2", response.getTransactionNumber());
        assertEquals("1", response.getBankTransactionNumber());
        assertEquals(true, response.isAuto());
    }

    @Test
    public void makeAuthenticationRequest() throws Exception {
        AuthenticationRequest authenticationRequest = banklink.prepareRequest(new AuthenticationRequestParams("RETURN_URL"));

        String htmlForm = authenticationRequest.createRequestForm();
        assertEquals(true, htmlForm.contains(authenticationRequest.getRequestData().get(Fields.NONCE)));
        assertEquals(true, htmlForm.contains("EYP"));
        assertEquals(true, htmlForm.contains(authenticationRequest.getRequestData().get(Fields.DATETIME)));
        assertEquals(true, htmlForm.contains("008"));
        assertEquals(true, htmlForm.contains("4012"));
        assertEquals(true, htmlForm.contains(authenticationRequest.getRequestData().get(Fields.MAC)));
        assertEquals(true, htmlForm.contains("RETURN_URL"));

        AuthenticationResponse authenticationResponse = (AuthenticationResponse) banklink.handleResponse(new HashMap<String, String>() {{
            put("VK_USER_NAME", "Tõõger Leõpäöld");
            put("VK_REC_ID", "uid55");
            put("VK_OTHER", "");
            put("VK_SERVICE", "3013");
            put("VK_SND_ID", "HP");
            put("VK_DATETIME", "2017-03-20T15:43:49+0000");
            put("VK_VERSION", "008");
            put("VK_MAC", "eyH2NaD6ylNxqmA6Kfy3uutZXH6AnqAVP4JqTxichV8H/WrlKK/QuFpekbjbbigVfzVPjjovzM9OuEDCid/5kEovBeDXJAqLD0uQ6k8aSRAshMGJvVBis6Iy6FPSye7m/5BJjkDw0aXAv6m34wug7BOQ96YxMuh7zypDBMQSjtszv37DrIbQSwV0gOFmyC9s65VGeDqGCjih1dSl4GAnjoX5Ewb48c7/cAdysZKPDNXm32w8Mdm1lrd/I3SnesnVsP7D4z9rKsrfXGX4tMfBJmf5SEGiRWGWNboeHafjd7vHh7AAEWKBMhvheaXuDSbAsq1uTKBnCJ7JOnoqL1drTg==");
            put("VK_COUNTRY", "EE");
            put("VK_TOKEN", "5");
            put("VK_NONCE", "47dcfc47-08f2-4e97-ae50-4be343425203");
            put("VK_RID", "");
            put("VK_USER_ID", "37602294565");
        }});

        assertEquals("HP", authenticationResponse.getSenderId());
        assertEquals("EE", authenticationResponse.getCountryCode());
        assertEquals("", authenticationResponse.getMessage());
        assertEquals("Tõõger Leõpäöld", authenticationResponse.getName());
        assertEquals("37602294565", authenticationResponse.getSocialSecurityNumber());
      //  assertEquals("2017-03-20T15:43:49+0000", authenticationResponse.getTimestamp());
    }

*/
}