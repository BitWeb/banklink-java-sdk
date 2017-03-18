import ee.bitweb.banklinksdk.Banklink;
import ee.bitweb.banklinksdk.seb.Seb;
import ee.bitweb.banklinksdk.protocol.Vendor;
import ee.bitweb.banklinksdk.protocol.Protocol;
import ee.bitweb.banklinksdk.protocol.iPizza.PaymentRequest;
import ee.bitweb.banklinksdk.protocol.iPizza.iPizzaProtocol;
import ee.bitweb.banklinksdk.request.PaymentRequestParams;
import ee.bitweb.banklinksdk.seb.SebFields;

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
                    "http://localhost/banklink-return/seb",
                    "http://localhost/banklink-return/seb"
            );

        protocol.setTestMode(true);
        Banklink seb = new Seb(protocol);

        PaymentRequest paymentRequest = seb.preparePaymentRequest(new PaymentRequestParams("1", 0.01, "BitWeb test", "643519"));

        String html = paymentRequest.createRequestHtml();

        System.out.println(html);



        seb.prepareAuthenticationRequest();

    }

}
