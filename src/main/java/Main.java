import ee.bitweb.banklink.sdk.Banklink;
import ee.bitweb.banklink.sdk.params.PaymentRequestParams;
import ee.bitweb.banklink.sdk.protocol.iPizza.iPizzaProtocol;
import ee.bitweb.banklink.sdk.protocol.iPizza.request.AuthenticationRequest;
import ee.bitweb.banklink.sdk.protocol.iPizza.request.PaymentRequest;
import ee.bitweb.banklink.sdk.protocol.iPizza.response.AuthenticationResponse;
import ee.bitweb.banklink.sdk.params.AuthenticationRequestParams;
import ee.bitweb.banklink.sdk.banks.seb.Seb;
import ee.bitweb.banklink.sdk.protocol.Vendor;
import ee.bitweb.banklink.sdk.protocol.Protocol;
import ee.bitweb.banklink.sdk.protocol.iPizza.response.PaymentResponse;

import java.util.HashMap;

/**
 * Created by tobre on 18/03/2017.
 */
public class Main {

    protected static String privateKey ="-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAwC0ecXChITo1mKDctdWAgtK1CjquE6axMmhfmav89z096oLr\n" +
            "xr0zPA0D5DH9tnuTFuszp6jHi8SEA/V9rvpVjReFQMMHYi5Z4qXuDjo6y48pT4VL\n" +
            "8KVVzjJvSYhkXmoc6N1G1VCV+ylHTZHrOUjwyv+xo1NhZs3Wd3mGkvmZPWu2PM7C\n" +
            "4LKviH93Znc0Q/2gzFWsZkg+ipMVilkzHTLUtTbiFX0v3UfI9NdtsiW70E4NU4Fx\n" +
            "PuiZTtngnDj8kWSHUhQGOAPNATqJWJXksUbElRe0lTErEp70fQuCbZzszdSCuq2f\n" +
            "ty5ffdw9RTQ9ovEOgFausqXcODSJlXu8A/Wh2QIDAQABAoIBAQCsjNZ0zMggaX+O\n" +
            "F4GU0qqczEw5bw62HkmkFQshk8eCxr49Pmgs/dxu4tDHqAtCOhqLzYuH0oe4+y39\n" +
            "0AKVhbgjAydmSrbqdOvIEWx54dbAxz40ZF8XJ5yvk6FQyOI109k5TNU42y6PYTTg\n" +
            "dVBbEx8BZiw9lbQy9kbG55rphKsO9YUvINkka1XQGv3VBK9ndwtM76dY42NWYd1x\n" +
            "fPIHTqwygpcW8sv3CaQyU1XTon+xtbOXcffJgcHZjIMyyTHOCUbRuZQ5PKAqMM1O\n" +
            "UTiK2vXDej2p6yOXNmDgSOEoD/sANaEzzgGixhDH+sXsdSehX8Q3kXCo32nSlDwb\n" +
            "4ynQDjQBAoGBAOOARYwW5fuUs9UnE7WAYrexhcgjV2DNOE2VjH+vzs/JvNF44Tmb\n" +
            "tNu0trJwcWd+/FybTsaTgOZTB4JgqrfOGTF9+f1jXJI7ku/aapSCgUhuD4nfB0Xq\n" +
            "7fk/QDh+f15b+0OsIeVB+2JYTRnCPUpBpwvUQd/ntTqjujEyb8UkAyE5AoGBANhA\n" +
            "BChW0ql/Bl/TCH+iLse+y46tJoPwHGDjKTqoHoScMubA3d4p89dtuRW3aOUVYXfc\n" +
            "xR/xi6v5RVJ0XxJsIcaUfvCcwScCNZEb47EYAwhvs9CNKUMZEKygDiQLwQ1Aef/U\n" +
            "ThEmoXVVQoJ6MrVmlPiZb8RKD5PXWDnLM1ZqNKWhAoGAYU78cG9a78ErKoEMbRX9\n" +
            "I5VLI8/6D+23p3fQIM2SKE6h9Pc/XuS69z7U5n4yA5LNXs8PYuC0wc/B5oJygywk\n" +
            "mobH5xoY/nN85hsGe39WzSRGgTJkagOSxonHbmKtGC8/yjJWTTlrzST31Yy/qoNq\n" +
            "cCTaCGuTmQFvIpvwMuK0dAECgYEAhAhiAXcxXEewTqV81ODjCxYVFuHlWD0sNoO7\n" +
            "0pJ/P5xW/8jWLiUTOn9FuFqpcuVhA1wKB3zlOHUTvEpNGeRnouVbs/EhhX210wli\n" +
            "NehhQa9h7H2jqdIN+jgnKh77Geo6Etc/av1ZK1iJKT0O5O7jGf4k3YYSiMO8nFzS\n" +
            "1Xo29sECgYAxVI77ftYiRNhWYYjVGfXe3+HhmHR+QbeD2083z8KBN6V22oN5ikgp\n" +
            "wxp0GeFFvkHJ3JpPQ6Kp5y0c1tFq7rnAT9kFHm7zv/KgamRNFuE81kk8WS0CzqHD\n" +
            "zF8Fq8IZMJE7OwKDkHksbCnMtVpJEx+G7GFDpNX5LpAS8PvTGRs/1A==\n" +
            "-----END RSA PRIVATE KEY-----";

    protected static String publicKey ="-----BEGIN CERTIFICATE-----\n" +
            "MIIDmDCCAoACCQCT1E6ysTlQKDANBgkqhkiG9w0BAQUFADCBjTELMAkGA1UEBhMC\n" +
            "RUUxETAPBgNVBAgMCEhhcmp1bWFhMRAwDgYDVQQHDAdUYWxsaW5uMRIwEAYDVQQK\n" +
            "DAlCaXRXZWIgT1UxETAPBgNVBAsMCGJhbmtsaW5rMRIwEAYDVQQDDAlsb2NhbGhv\n" +
            "c3QxHjAcBgkqhkiG9w0BCQEWD3RvYnJlQGJpdHdlYi5lZTAeFw0xNzAzMjAxMTQw\n" +
            "MzFaFw0zNzAzMTUxMTQwMzFaMIGNMQswCQYDVQQGEwJFRTERMA8GA1UECAwISGFy\n" +
            "anVtYWExEDAOBgNVBAcMB1RhbGxpbm4xEjAQBgNVBAoMCUJpdFdlYiBPVTERMA8G\n" +
            "A1UECwwIYmFua2xpbmsxEjAQBgNVBAMMCWxvY2FsaG9zdDEeMBwGCSqGSIb3DQEJ\n" +
            "ARYPdG9icmVAYml0d2ViLmVlMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKC\n" +
            "AQEApAlCJVgJ8TaoimxVW+EHlIj9YnvG7S2E6n63BWKyEu7E3ff2q1UmUesTRoSU\n" +
            "GJzIIIhf7AL188UKbkN3EQAzeitQF3QX6EE+QGSXFQ9xbVsDqgSSI9+FIJApttav\n" +
            "/WQWzO+I3dCByTURQRWLJB4J0dMUutaLO/K6KncIV2Lkbi7g8XZYm0BJPeW0ODez\n" +
            "ji5X2UxTS8RtJqXN8EQjs5Uj/3u75DBfYuJSERX9p+ge1Mj6QlWutqKEhRU30/Pj\n" +
            "fQapmv1xTFIPkgVjw9Ah2BehqVa1BhU3CsrS9/QpSsqZbODwXOKgsRY5GjKqoABQ\n" +
            "kQQA7pdewSDPv6zNlxDYK59ZrQIDAQABMA0GCSqGSIb3DQEBBQUAA4IBAQA78DrC\n" +
            "Yf9ieyZ3HWd93U5vgfb47Ppa0e/WAOytWvqFIw1XQDCBWjs/eBOInG/PmDuAaFw/\n" +
            "EJFYDRekpy9EWQbPe0hPomPy/IyvMiUDHgNEhbSVpwaURVsW3QtiuQg5nOE7VLhp\n" +
            "dWYDAoHGrA61auKBlGvVGmUY5PKr7PbL+a3Cidi/f04CiwZNoVlj4DpUfH0XZ6On\n" +
            "g6qaTCSc8TKo9u5zmdh2vkYNB3tzflZoPU7DCPiqKrDuqMXF2vmNlmBV8n+EJ5fj\n" +
            "VI/503GQBwRChFw6AH9R9pbj+m17s76QmuLczF6sZW6yP60c310gyrWCbMhjZUO7\n" +
            "0cemX/g0MnKyBxJm\n" +
            "-----END CERTIFICATE-----";

    public static void main(String args[]) {

        Protocol protocol = new iPizzaProtocol(
            publicKey,
            privateKey,
            new Vendor(
                    "uid39",
                    "Test Test",
                    "EE411010002050618003"),
                    "http://requestb.in/18d2oau1",
                    "http://requestb.in/18d2oau1"
            );

        protocol.setTestMode(true);
        Banklink seb = new Seb(protocol);
        /*PaymentRequest paymentRequest = seb.prepareRequest(new PaymentRequestParams("2", 0.01, "BitWeb test", "123"));

        String html = paymentRequest.createRequestHtml();

        System.out.println(html);


        PaymentResponse response = (PaymentResponse) seb.handleResponse(new HashMap<String, String>() {{
            put("VK_STAMP", "2");
            put("VK_AUTO", "N");
            put("VK_T_NO", "3");
            put("VK_VERSION", "008");
            put("VK_SND_ID", "EYP");
            put("VK_AMOUNT", "0.01");
            put("VK_T_DATETIME", "2017-03-20T14:53:45+0000");
            put("VK_CURR", "EUR");
            put("VK_SND_ACC", "EE171010123456789017");
            put("VK_SERVICE", "1111");
            put("VK_REC_ACC", "");
            put("VK_MSG", "BitWeb test");
            put("VK_SND_NAME", "Test Test");
            put("VK_REC_ID", "uid39");
            put("VK_REF", "123");
            put("VK_MAC", "aM/wkNCNTxTO2l2PDIM1HZTqmGwTt7IioHitsCWxHuKK6lcikAs58P78nypSv+SkOh02BTtKvVXFIDEwCnLJGLjczKQ+FINlKf0AZVbj+TDVUM8jc6X9lrpPyH/C6/q2khPtuizcZUfmLu1HH8utLarnfAJqq5WQc/8ncdjS3tJUvEcGczriz8lVUELViqVP1yJTly/XpT7xarroMzMssb3jPv6V5MQ9Xu2geF6pk2RL2g0abJOAvVp7b5DvmredELQQczZ1jh/BWkwrlobZFrprwmdmsgXT6efOSKb8CKORP8tvq6t7hyItBfmuhWJ4gnKLgk6HwzB6nRmVrb/0lw==");
            put("VK_REC_NAME", "");
            put("VK_LANG", "EST");
            put("VK_ENCODING", "UTF-8");
        }});
        System.out.println(response.getTransactionTimestamp());*/

        AuthenticationRequest authenticationRequest = seb.prepareRequest(new AuthenticationRequestParams());

        String html2 = authenticationRequest.createRequestHtml();

        System.out.println(html2);


        //004 3013
        //003 008
        //024 2017-03-20T15:29:22+0000
        //003 EYP
        //005 uid39
        //036 651e3650-306d-4912-a668-00974de2d6ba
        //015 Tõõger Leõpäöld
        //024 2017-03-20T15:29:22+0000
        //002 EE
        //000
        //001 5
        //000
        AuthenticationResponse authenticationResponse = (AuthenticationResponse) seb.handleResponse(new HashMap<String, String>() {{
            put("VK_USER_NAME", "Tõõger Leõpäöld");
            put("VK_REC_ID", "uid39");
            put("VK_OTHER", "");
            put("VK_SERVICE", "3013");
            put("VK_SND_ID", "EYP");
            put("VK_DATETIME", "2017-03-20T15:29:22+0000");
            put("VK_VERSION", "008");
            put("VK_MAC", "kiQJ/VW6koESn2kM+yrAUHHLVRDDoFRfbnsrEi6owv9qWNzmEucNYQQArc0of0nSLAlXHO79UYXVDpe+R5g0evZKSaml0LtFbwKMqxRz42Yggy3YDTJ7CkhaRt1uhGUy5m+j5GP1APtT4C6kMOF6VyAirnbEprfuKyH9qTqjQAAXLdQB3ILkRYQhuKCqzG1Sq3KjoGvmy5vvNMiXmx8W6tPOr0EpbbjTK0WWlvLGlx5kXilSfeBSpTo0fDOblBEC+Kpq+1wooanC5Dy8bsAe+2Vhi69LAAaqxKvm+NcDMJAxz+gqE1nvblZi7RVKlxHO0xOxyT1ckX7r3TxkBuJY7w==");
            put("VK_COUNTRY", "EE");
            put("VK_TOKEN", "5");
            put("VK_NONCE", "651e3650-306d-4912-a668-00974de2d6ba");
            put("VK_RID", "");
            put("VK_USER_ID", "37602294565");
        }});

        System.out.println(authenticationResponse.getSocialSecurityNumber());



    }

}
