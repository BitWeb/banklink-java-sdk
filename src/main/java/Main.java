import ee.bitweb.banklinksdk.Banklink;
import ee.bitweb.banklinksdk.protocol.iPizza.*;
import ee.bitweb.banklinksdk.protocol.iPizza.request.AuthenticationRequest;
import ee.bitweb.banklinksdk.protocol.iPizza.response.AuthenticationResponse;
import ee.bitweb.banklinksdk.params.AuthenticationRequestParams;
import ee.bitweb.banklinksdk.banks.seb.Seb;
import ee.bitweb.banklinksdk.protocol.Vendor;
import ee.bitweb.banklinksdk.protocol.Protocol;

import java.util.HashMap;

/**
 * Created by tobre on 18/03/2017.
 */
public class Main {

    protected static String privateKey ="-----BEGIN PRIVATE KEY-----\n" +
            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALvdyY/hPEU7VOcv\n" +
            "swtPVWsQntCbQAJMsQWJGL1EC0EJ3G5MpYa9MgcueUdGJ2Cj7+k7BgkInVtx98nH\n" +
            "0cx43sAqDuRAEIzoxAElma3f1BLY/LUk7bgMipwB49Tkicz2gfHTXy1xOyldFZcQ\n" +
            "TIWOPRvYhUhUsYAiM2DCuZfzete3AgMBAAECgYBbTw/Bc5qkl5LLjr5598QvdnOK\n" +
            "Nnrk4zuC0kCI9QJ3SQCvR2eIVnpzTR47CxjmeqeHd12WlG5O3NSvaQRKWlEAxgHC\n" +
            "OmEqZRGTqnO/1uCpiZEWZsMBsb2MC+Jt1EluLpO8o57j65Ytr6VzG/3u9nA7Z+b8\n" +
            "mPmt9IL4SoyKeme5OQJBANxrm2bqJ/PaXAMWC2suEGnFRX4KyZxxcb9aiiVdpOlL\n" +
            "c6pMZneMv2H92WduTjm0fee86bGQK/I3/hU3wpjjQW0CQQDaMPUeVi8G7oJwb9RL\n" +
            "rBcSR9b5ysK+QWIvc+ySipgy3+CPxdwNZSOilL/DAwR+Y2xgNBGD549eqTezPzL3\n" +
            "5aszAkEAveGxQOoMuuRsCXiPI6jOGOAJanhOkAembqrHgUL3ksYeASHJz8kfAYKW\n" +
            "K6T5nHphUCYMx5skUIhtyMd9SwnSzQJBAJFqm5LSYZk/EJQbE+QzF1VJp87nAY+H\n" +
            "SAbUPdEUqYcOij2H4rbSt0M92+f+dNo/LRWY3iuJApZWNRczgaaR3N0CQGD0TRzA\n" +
            "IE576xBHfVnaCOO9cbjYRSj5c7SjeStBK1cN1ggbJCJjMH5JZIpzNc5mV5p+yotr\n" +
            "SeIymXiA/fBTGzc=\n" +
            "-----END PRIVATE KEY-----";

    protected static String publicKey ="-----BEGIN CERTIFICATE-----\n" +
            "MIICkTCCAfoCCQDvOtxgR6zmIDANBgkqhkiG9w0BAQUFADCBjDELMAkGA1UEBhMC\n" +
            "RUUxETAPBgNVBAgTCEhhcmp1bWFhMRAwDgYDVQQHEwdUYWxsaW5uMQ0wCwYDVQQK\n" +
            "EwRUZXN0MREwDwYDVQQLEwhiYW5rbGluazEXMBUGA1UEAxMObG9jYWxob3N0IDg4\n" +
            "ODgxHTAbBgkqhkiG9w0BCQEWDnRlc3RAbG9jYWxob3N0MB4XDTE3MDMxODIwNDYw\n" +
            "OFoXDTM3MDMxMzIwNDYwOFowgYwxCzAJBgNVBAYTAkVFMREwDwYDVQQIEwhIYXJq\n" +
            "dW1hYTEQMA4GA1UEBxMHVGFsbGlubjENMAsGA1UEChMEVGVzdDERMA8GA1UECxMI\n" +
            "YmFua2xpbmsxFzAVBgNVBAMTDmxvY2FsaG9zdCA4ODg4MR0wGwYJKoZIhvcNAQkB\n" +
            "Fg50ZXN0QGxvY2FsaG9zdDCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAv1hH\n" +
            "iqw8qcvqcnAIq5K6K6nG0VNaTHD4orfoHsAGgruYFaKLMuo09CfZXiVLPwKXnpFW\n" +
            "l5plBuZPuec8d/693G/LW4hV8oMq8WheaucE2s+ZC3/V3bFcP7AU0D2XwO1k3Ihs\n" +
            "kTb8R5g0563otX2JSTQQmTmeG3vGuIkZKlPbhO0CAwEAATANBgkqhkiG9w0BAQUF\n" +
            "AAOBgQBjpGAViO3pgDmuSGg61+NNbngS58eWatcOXLq3WNK0H+2Z/iKXWXPhug7d\n" +
            "gUCmYn/n5eHQPa4J4habjx3RuiZbJZ+6zDuvWyjMdiJPwYEYCwZuvz2Y9mjAn55W\n" +
            "yIRwpYT3OZPPlbfMFwBl5cNdZxows+hPindIalQMJw0zr6KOiw==\n" +
            "-----END CERTIFICATE-----";

    public static void main(String args[]) {

        Protocol protocol = new iPizzaProtocol(
            publicKey,
            privateKey,
            new Vendor(
                    "uid100010",
                    "TIIGER LEOPOLDÃµ",
                    "EE411010002050618003"),
                    "http://requestb.in/po9q6lpo",
                    "http://requestb.in/po9q6lpo"
            );

        protocol.setTestMode(true);
        Banklink seb = new Seb(protocol);

        //PaymentRequest paymentRequest = seb.prepareRequest(new PaymentRequestParams("1", 0.01, "BitWeb test", "643519"));

        //String html = paymentRequest.createRequestHtml();

        //System.out.println(html);

        /*PaymentResponse response = (PaymentResponse) seb.handleResponse(new HashMap<String, String>() {{
            put("VK_STAMP", "1");
            put("VK_AUTO", "Y");
            put("VK_T_NO", "10002");
            put("VK_VERSION", "008");
            put("VK_SND_ID", "EYP");
            put("VK_AMOUNT", "0.01");
            put("VK_T_DATETIME", "2017-03-18T23:13:20+0200");
            put("VK_CURR", "EUR");
            put("VK_SND_ACC", "EE171010123456789017");
            put("VK_SERVICE", "1111");
            put("VK_REC_ACC", "EE411010002050618003");
            put("VK_MSG", "BitWeb test");
            put("VK_SND_NAME", "Tõõger Leõpäöld");
            put("VK_REC_ID", "uid100010");
            put("VK_REF", "643519");
            put("VK_MAC", "g+S+iTn9JUZ0n9AxU0wSaXa+ki5ouWN2k2KB5r2ZrOkSHEEFOYkOFko4422n0EoYjsexcAnP4LKP/X8KucisD2Icz7lwAVyjbEVW6sYGjVj6SDhBWOav0fDnv1pMExAb6f9RVZu+UHQe7z7ctZ/YPUN4EYH7UM9SVFZfZSIKg/0=");
            put("VK_REC_NAME", "TIIGER LEOPOLDÃµ");
            put("VK_LANG", "EST");
            put("VK_ENCODING", "UTF-8");
        }});
        System.out.println(response.getTransactionTimestamp());*/

        AuthenticationRequest authenticationRequest = seb.prepareRequest(new AuthenticationRequestParams());

        String html2 = authenticationRequest.createRequestHtml();

        AuthenticationResponse authenticationResponse = (AuthenticationResponse) seb.handleResponse(new HashMap<String, String>() {{
            put("VK_USER_NAME", "Tõõger Leõpäöld");
            put("VK_REC_ID", "Tõõger Leõpäöld");
            put("VK_OTHER", "");
            put("VK_SERVICE", "3013");
            put("VK_SND_ID", "EYP");
            put("VK_DATETIME", "2017-03-18T23:13:20+0200");
            put("VK_VERSION", "008");
            put("VK_MAC", "neP1ZmCST4M8uetVhVhf1UotL9gy1b63pojbO5EeJpjshQCpLI9rT7BH2tBkKrBQpBZycGvWdpIxzIo3lWDRdF3cDidXSgoF399f5E+ol8P5ADl9noyua7+L1cGUeTQBIjBifnCeSvdbXU3j5rkk59irlZqbRZGBZubXO5bQwSI=");
            put("VK_COUNTRY", "EE");
            put("VK_TOKEN", "5");
            put("VK_NONCE", "f7bdee09-125e-43bf-be27-9d5f963beede");
            put("VK_RID", "");
            put("VK_USER_ID", "37602294565");
        }});

        System.out.println(authenticationResponse.getSenderId());

        System.out.println(html2);

    }

}
