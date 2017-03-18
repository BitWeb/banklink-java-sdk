import ee.bitweb.banklinksdk.Banklink;
import ee.bitweb.banklinksdk.protocol.FieldDefinition;
import ee.bitweb.banklinksdk.protocol.iPizza.Fields;
import ee.bitweb.banklinksdk.protocol.iPizza.Response;
import ee.bitweb.banklinksdk.seb.Seb;
import ee.bitweb.banklinksdk.protocol.Vendor;
import ee.bitweb.banklinksdk.protocol.Protocol;
import ee.bitweb.banklinksdk.protocol.iPizza.PaymentRequest;
import ee.bitweb.banklinksdk.protocol.iPizza.iPizzaProtocol;
import ee.bitweb.banklinksdk.request.PaymentRequestParams;
import ee.bitweb.banklinksdk.seb.SebFields;

import java.util.HashMap;
import java.util.Map;

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

    protected static String publicKey =
            "MIICTTCCAbYCCQDyGqVt0TTWTDANBgkqhkiG9w0BAQsFADBrMQswCQYDVQQGEwJF\n" +
            "RTEOMAwGA1UECAwFVGFydHUxDjAMBgNVBAcMBVRhcnR1MQwwCgYDVQQKDANhc2Qx\n" +
            "DDAKBgNVBAsMA2FzZDEMMAoGA1UEAwwDYXNkMRIwEAYJKoZIhvcNAQkBFgNhc2Qw\n" +
            "HhcNMTcwMzE4MTcyMDQ4WhcNMTgwMzE4MTcyMDQ4WjBrMQswCQYDVQQGEwJFRTEO\n" +
            "MAwGA1UECAwFVGFydHUxDjAMBgNVBAcMBVRhcnR1MQwwCgYDVQQKDANhc2QxDDAK\n" +
            "BgNVBAsMA2FzZDEMMAoGA1UEAwwDYXNkMRIwEAYJKoZIhvcNAQkBFgNhc2QwgZ8w\n" +
            "DQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAL0EIrY3QnMBxNYXDuHNVTxaA86spECJ\n" +
            "093l3J92m8GE6YEQhZ3YoLUi2ZrQGVT5CQMm1mLfqV0fiupkc2YJginlwjPNI5OR\n" +
            "Jq4afuaaQeDiO6+hQKs0ctHrvrtYdaOc5iKf40lkQZnonHwQ9Fwz0s5bx4EwFT9S\n" +
            "3ynyjVUckdhXAgMBAAEwDQYJKoZIhvcNAQELBQADgYEAa0vcJcbjcMIM9fDpWIZx\n" +
            "y1CNysOnNJLE6AfvuMR/l292Z1q6QJFNz6IQnr3UpeByDzlJqPIddeRYrJKvZVo/\n" +
            "dHPG7NOFJlHf64VDmWFobswMjO2ABbPejGlVDSnCuQ9zf2Tcrp0vo/8LxSLBHO83\n" +
            "uIzzHtAu+2oVGx60/9AoXw8=";

    public static void main(String args[]) {

        Protocol protocol = new iPizzaProtocol(
            publicKey,
            privateKey,
            new Vendor(
                    "uid100010",
                    "TIIGER LEOPOLDÃµ",
                    "EE411010002050618003"),
                    "http://requestb.in/rdogj8rd",
                    "http://requestb.in/rdogj8rd"
            );

        protocol.setTestMode(true);
        Banklink seb = new Seb(protocol);

        PaymentRequest paymentRequest = seb.preparePaymentRequest(new PaymentRequestParams("1", 0.01, "BitWeb test", "643519"));

        String html = paymentRequest.createRequestHtml();

        System.out.println(html);


        Response response = seb.handleResponse(new HashMap<String, String>() {{
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


        seb.prepareAuthenticationRequest();

    }

}
