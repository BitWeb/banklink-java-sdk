import ee.bitweb.banklink.sdk.Banklink;
import ee.bitweb.banklink.sdk.banks.lhv.Lhv;
import ee.bitweb.banklink.sdk.banks.swedbank.Swedbank;
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

    protected static String publicKey ="-----BEGIN CERTIFICATE-----\n" +
            "MIIDmDCCAoACCQDmJcXiSznEbDANBgkqhkiG9w0BAQUFADCBjTELMAkGA1UEBhMC\n" +
            "RUUxETAPBgNVBAgMCEhhcmp1bWFhMRAwDgYDVQQHDAdUYWxsaW5uMRIwEAYDVQQK\n" +
            "DAlCaXRXZWIgT1UxETAPBgNVBAsMCGJhbmtsaW5rMRIwEAYDVQQDDAlsb2NhbGhv\n" +
            "c3QxHjAcBgkqhkiG9w0BCQEWD3RvYnJlQGJpdHdlYi5lZTAeFw0xNzAzMjAxMTQw\n" +
            "NTRaFw0zNzAzMTUxMTQwNTRaMIGNMQswCQYDVQQGEwJFRTERMA8GA1UECAwISGFy\n" +
            "anVtYWExEDAOBgNVBAcMB1RhbGxpbm4xEjAQBgNVBAoMCUJpdFdlYiBPVTERMA8G\n" +
            "A1UECwwIYmFua2xpbmsxEjAQBgNVBAMMCWxvY2FsaG9zdDEeMBwGCSqGSIb3DQEJ\n" +
            "ARYPdG9icmVAYml0d2ViLmVlMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKC\n" +
            "AQEA78AhyzVHLOQsPO/2jxA36dAT/X6OVes8b9p8vlG9ybuDPbci6NmuKhBTaZkc\n" +
            "xAoqL5qY7UfZDeQvL+cKmLpxGEtGvz515JozPkHfvY9+xqTRdYu5t6rEvp2a6oA4\n" +
            "Q1SxHvFgX9SaoJiF+EVWc+TLJOtSiKifJ/cHM9aoDTB9Dilm3C5c/yx2CJ5SPFY/\n" +
            "cVWbnwfXq00D3mYbP2KHXJrEW+JoFG2EwsSbemOeTrJCtp31g4PiE82r9y05ywcQ\n" +
            "r79uXlO+uWgOQp3P/Ms9wQ95ALwPOyMlLExm7D9GI5rodTLq/0ctbzOPKPsGR6nw\n" +
            "Lk0GTPhsjR9j8agSL0awfk3MoQIDAQABMA0GCSqGSIb3DQEBBQUAA4IBAQDIB2YC\n" +
            "dL+lXko8GixQ863pesUDnDX1BwXXOpiA88B8DD1kUbuEafpBa9TxtZvFUyDVw2E9\n" +
            "yYG/C7GsiMnWmUhfxBg6uXl3BGUTCZvOVvjZQM0JCjtS6TfmZfSmCQOnpcoGr6tr\n" +
            "HG/pI2MzZgasuMX9Bgp9mZhoaC5NR1XC3CVbmYnP8UyuRHqoeu5w9S1hBVCsWxZA\n" +
            "LL2W+ZDuerwH7VFPcUh6COsiHWY2ov8BpHYndqfFBKf6FiRqvF3Tseomtnvoj8V6\n" +
            "aT+8otiVU4qz1sfLglr95L/JDahFBpMcL/Errt7gnh2a3vDPQm1hBs9IKwSpnf+s\n" +
            "sQ3iituJ6Q0L045j\n" +
            "-----END CERTIFICATE-----";

    public static void main(String args[]) {

        Protocol protocol = new iPizzaProtocol(
            publicKey,
            privateKey,
            new Vendor(
                    "uid42",
                    "Test Test",
                    "EE411010002050618003"),
                    "http://requestb.in/18d2oau1",
                    "http://requestb.in/18d2oau1"
            );

        protocol.setTestMode(true);
        Banklink seb = new Lhv(protocol);
        PaymentRequest paymentRequest = seb.prepareRequest(new PaymentRequestParams("2", 0.01, "BitWeb test", "123"));

        String html = paymentRequest.createRequestHtml();

        System.out.println(html);


        PaymentResponse response = (PaymentResponse) seb.handleResponse(new HashMap<String, String>() {{
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
        System.out.println(response.getTransactionTimestamp());

        AuthenticationRequest authenticationRequest = seb.prepareRequest(new AuthenticationRequestParams());

        String html2 = authenticationRequest.createRequestHtml();

        System.out.println(html2);


        AuthenticationResponse authenticationResponse = (AuthenticationResponse) seb.handleResponse(new HashMap<String, String>() {{
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

        System.out.println(authenticationResponse.getSocialSecurityNumber());



    }

}
