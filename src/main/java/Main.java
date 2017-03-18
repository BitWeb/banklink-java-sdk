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
            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANhCJFjd/O/Ztnhv\n" +
            "3sLvzSDbPoUf6xRbePiwgJZcGoyC9M2XolwhHDOg1wHIpYiMKpEFOI5VzsFCHRHa\n" +
            "1GJvcOQa17wxgAjuPpWOw6ErEEvaZU4AuGHqlWe/xzAVkjd3o3dnoHu/zMcak7/O\n" +
            "ZF9Uyx2V9HgVSeYZEnp7S4MC3XR1AgMBAAECgYBeZ1k4lgdxqMeYAbXrwQT539uF\n" +
            "/63+YAQKA/rOKHzKc+xrx1LwBuApxX+2XqGsXwvB7NaIg/rSsIrCHZXzNpS/oUIe\n" +
            "KRDUeDJHVH0LLnIodG1+QfLTUFhQfF4hoMlPu0e0HAdPyWm08z3JxdxN8WoqXgvm\n" +
            "wXj4NRxs2tji5yoHTQJBAPUsmJ5ZQeWNvVvtq5EM0iWspUKZccGc0P4s8X8r29hd\n" +
            "+PDNseUny1r1M1Sn8A5QI0c8mS7C1dD9ZJIWby/A7YMCQQDhzq/BlX06iy08WCsG\n" +
            "8kKI65GG6NWy1v1i6m38rb4FRLrbsWwUPWU4x98dmG3BslMUvYA72fjKr2OkTunJ\n" +
            "ECynAkEAtvQte1St+OfZW2Uy9w2zL8vyko1AtzMXPeU53SVUa9RMNR2513/nnxuE\n" +
            "z3J2j6e7e6g7wwo/VjIQv3cgR2lXkwJARIcGPbiRTaAJNWQIgs4otQbQnmGw4Npw\n" +
            "AYHlqSZNIaovDxc0FpxfSLEXnBKzVHI61nOAJCnNYC2EDsxIFNXMxwJBAKTWkmwS\n" +
            "loNy+cYExJCsQN9kUdJwv8YRlGNSIPtzXf9IanU61SJfX0IK8m4SagCivv6OQxtu\n" +
            "1Tp9zl6uDd1gcyw=\n" +
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
