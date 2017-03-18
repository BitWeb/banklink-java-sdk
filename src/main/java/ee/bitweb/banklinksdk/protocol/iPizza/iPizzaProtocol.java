package ee.bitweb.banklinksdk.protocol.iPizza;

import ee.bitweb.banklinksdk.exception.BanklinkException;
import ee.bitweb.banklinksdk.protocol.FieldDefinition;
import ee.bitweb.banklinksdk.protocol.Protocol;
import ee.bitweb.banklinksdk.protocol.Vendor;
import ee.bitweb.banklinksdk.request.PaymentRequestParams;
import ee.bitweb.banklinksdk.response.ResponseParams;
import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

/**
 * Created by tobre on 18/03/2017.
 */
public class iPizzaProtocol extends Protocol {

    protected String publicKey;
    protected String privateKey;

    protected Vendor vendor;

    protected String successUri;
    protected String cancelUri;


    public iPizzaProtocol(String publicKey, String privateKey, Vendor vendor, String successUri, String cancelUri) {
        //TODO: Check parameters and throw IllegalArgumentException

        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.vendor = vendor;

        this.successUri = successUri;
        this.cancelUri = cancelUri;
    }

    @Override
    public Map<FieldDefinition, String> preparePaymentRequest(PaymentRequestParams paymentRequestParams) {
        Map<FieldDefinition, String> requestData = new HashMap<>();
        requestData.put(Fields.SERVICE, Services.PAYMENT_REQUEST.code);
        requestData.put(Fields.VERSION, version);

        requestData.put(Fields.SND_ID, vendor.getSenderId());
        requestData.put(Fields.STAMP, paymentRequestParams.getTransactionId());
        requestData.put(Fields.AMOUNT, String.valueOf(paymentRequestParams.getAmount()));
        requestData.put(Fields.CURR, paymentRequestParams.getCurrency());
        requestData.put(Fields.REF, paymentRequestParams.getReferenceNumber());
        requestData.put(Fields.MSG, paymentRequestParams.getMessage());
        requestData.put(Fields.RETURN_URL, paymentRequestParams.getSuccessUri() == null ? successUri : paymentRequestParams.getSuccessUri());
        requestData.put(Fields.CANCEL_URL, paymentRequestParams.getCancelUri() == null ? cancelUri : paymentRequestParams.getCancelUri());
        requestData.put(Fields.DATETIME, formatDate(new DateTime()));

        requestData.put(Fields.ENCODING, paymentRequestParams.getEncoding());
        requestData.put(Fields.LANG, paymentRequestParams.getLanguage());


        try {
            requestData.put(Fields.MAC, getRequestSignature(getMac(requestData, Services.PAYMENT_REQUEST)));
        } catch (Exception e) {
            throw new BanklinkException(e);
        }

        return requestData;

    }

    //FIXME: Encapuslate to Helper


    public RSAPrivateKey getPrivateKeyFromString() throws IOException, GeneralSecurityException {
        String privateKeyPEM = privateKey;
        privateKeyPEM = privateKeyPEM.replace("-----BEGIN PRIVATE KEY-----", "");
        privateKeyPEM = privateKeyPEM.replace("-----END PRIVATE KEY-----", "");
        byte[] encoded = Base64.decodeBase64(privateKeyPEM);


        KeyFactory kf = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        RSAPrivateKey privKey = (RSAPrivateKey) kf.generatePrivate(keySpec);
        return privKey;
    }




    protected String getRequestSignature(String mac) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException, GeneralSecurityException, IOException {
        RSAPrivateKey privKey = getPrivateKeyFromString();
        Signature s = Signature.getInstance("SHA1withRSA");
        s.initSign(privKey);
        s.update(ByteBuffer.wrap(mac.getBytes()));
        byte[] signature = s.sign();

        return new String(Base64.encodeBase64(signature));
    }

    protected String getMac(Map<FieldDefinition, String> requestData, Services service) {
        String data = "";

        for (FieldDefinition field : Services.getFields(service)) {
            data += padMacParameter(requestData.get(field));
        }

        return data;
    }

    private String formatDate(DateTime date) {
        DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ");

        return date.toString(df);
    }

    private String padMacParameter(String value) {
        String length = String.valueOf(value.length());
        while (length.length() < 3) {
            length = "0" + length;
        }

        return length + value;
    }

    @Override
    public Response handleResponse(Map<String, String> responseParams) {
       // if (responseParams)


        return handlePaymentResponse(responseParams);

    }

    protected PaymentResponse handlePaymentResponse(Map<String, String> responseParams) {
        return new PaymentResponse();
    }
}
