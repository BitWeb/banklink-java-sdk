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
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL4BE6VeJG+LVPtD\n" +
            "18CHQvDvezMCfbqap8oOp3oF2mWre/6BVYLe/T5uC181qRET9RVg7EQDTPS+KpGk\n" +
            "rM+43Lw6y1Mx7x8UqzX01byUFiKTo3MngGT4HpWai86bScktAnK1cqInK4c/0Oo8\n" +
            "5WY/xL5rPNftSrOi/WfGj1+8fvLZAgMBAAECgYAWFsP07qvnt9gKWgnEHTWAEydM\n" +
            "d9asEfy9tdRskC+isDv7C4gobcodLuftiqx8CsjsUldFVOjmbxE921on0AMBP2uM\n" +
            "CLcDXtNoS57ImX4H+GMdiGx3wx4WFNvZ+HStjcXp2IvxT9gNuIAh6Cco7TNxzrKc\n" +
            "muxBm/NvoOVj8Is97QJBAPcpcnPDVl8nW2xzfvRv9kLVitFRHDjVDQjyWNgnwQnD\n" +
            "+eBXGucIix3jdtGc5Wn2C4muNNBGSEMLcacnZjT7jYsCQQDEzGaHtBjici0FNLR9\n" +
            "7UoJq3hUPGHLFgSFkqUYOHFbvm81E6uuKkHVl8GyrnGRjYwz1HtYqYvtKlK2tQ8i\n" +
            "8RWrAkEA5cDghJ3rVy6lvK2sMRs2xy/sB+GnI2lg4ZUuY1ipCCPlztYJywPVFiaR\n" +
            "GA8J7zLpzMVkkMsUd0ZXEvP7/XptOwJAL0k5vZOdgxOSE7UlRTi12Hr176+Og+Cn\n" +
            "d3zT5Gzzd+rbB7LRRojqxqqvJQLbUPDk5jNA6/3ZHERWGYaJu4iX/wJAKJivPCVk\n" +
            "kOWwqtY9lPQTyvM2YtmSE0fNgk/1LvH4UqSbeAV5cQGDMaf1Q53rEgCnSnsDVdUE\n" +
            "3xfMMxrT2BI6Sw==\n" +
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
                new Vendor("testvpos", "TIIGER LEOPOLDÃµ", "EE411010002050618003"),
                "http://localhost/return",
                "http://localhost/return"
        );

        protocol.setTestMode(true);
        Banklink seb = new Seb(protocol);

        PaymentRequest paymentRequest = seb.preparePaymentRequest(new PaymentRequestParams("Super ID", 12.0, "Laheee", "Minu Vitunumber"));

        String html = paymentRequest.createRequestHtml();

        System.out.println(html);



        seb.prepareAuthenticationRequest();

    }

}
