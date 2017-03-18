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

    protected static String privateKey = "-----BEGIN RSA PRIVATE KEY-----" +
            "MIICXQIBAAKBgQDfsdR/02PcAvH1QzP3AhG1NJFggWbHL6W0Ji4XlGrJtZG4S0xX" +
            "bZL/37q1+IU4a3hqRUqBu+Z9dQ1Cn2JeF0vJOQW8QV1fslY3rajMUIQjKa/keUoe" +
            "EBkQBVHGzes35tOheeuSGU7TBSrFCY06x0AWIWSzuxiqZcyomwfFCobGAwIDAQAB" +
            "AoGAV9jVU9GnULqTXLckjCdy3s+jG9wVibmrgGDSDw6JFWfJry9a7qaoaPXqtBXB" +
            "85M4+br2dJZfAPvb1kMTdVH+v24qXzi6TYEeRTNXqVC9Z24+9p34rx/jcgKNYuw3" +
            "aUBI6uIe/L0LDK0LBDjn/IYtZ7imu/fNhOuOBXFJK7u230ECQQD23G07+6+GTgET" +
            "hPOuqtNQMRlfu0gCsjHRWDicrmibbiR5AP7UbX8b6PoNwCevf3LqLbGQZWJG5if0" +
            "UPPUEsChAkEA5/nZSkH8Sloht740Hl2FzenP3/uprJMFz6NISlcBQA4xm0+0mbLK" +
            "JcGdvxgHdqAfYXG/d+b832abZSEEybBwIwJBAOdxmmhWfCYrFA8840qrlhrlLaj3" +
            "aXg9oT9ouwsK6cBv73oTWNfJIRcFwwsJUSvVDeRImuWKaC2Ys3T4QOJgAoECQAgg" +
            "gLRRS31vWu8NAtZtgFqObnHyZydcnz9/APFZyvlrheFjEXixAtP/zH79YIWPZooL" +
            "aiHRPdq1xYvtdDV8sPECQQDcF3qv9UTxeR7fHv7BM8tE3gRSfb+81s5ndq7n+dDQ" +
            "LLvyUf0n8ojvgGvCZIWISTt544s2j/CB+/EI5QhmBF65" + "-----END RSA PRIVATE KEY-----";

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
                new Vendor("sender", "name", "account"),
                "http://localhost/return",
                "http://localhost/return"
        );
        Banklink seb = new Seb(protocol);

        PaymentRequest paymentRequest = seb.preparePaymentRequest(new PaymentRequestParams(12.0, "Laheee", "Minu Vitunumber"));

        String html = paymentRequest.createRequestHtml();

        System.out.println(html);



        seb.prepareAuthenticationRequest();

    }

}
