package ee.bitweb.banklinksdk.protocol.iPizza;

import ee.bitweb.banklinksdk.exception.BanklinkException;
import ee.bitweb.banklinksdk.protocol.FieldDefinition;
import ee.bitweb.banklinksdk.protocol.Protocol;
import ee.bitweb.banklinksdk.protocol.Vendor;
import ee.bitweb.banklinksdk.request.PaymentRequestParams;

import java.nio.ByteBuffer;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by tobre on 18/03/2017.
 */
public class iPizzaProtocol extends Protocol {

    protected String publicKey;
    protected String privateKey;

    protected Vendor vendor;


    public iPizzaProtocol(String publicKey, String privateKey, Vendor vendor) {
        //TODO: Check parameters and throw IllegalArgumentException

        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.vendor = vendor;
    }

    public Map<FieldDefinition, String> preparePaymentRequest(PaymentRequestParams paymentRequestParams) {
        Map<FieldDefinition, String> requestData = new HashMap<>();
        requestData.put(Fields.SERVICE, Services.PAYMENT_REQUEST.code);
        requestData.put(Fields.VERSION, version);

        requestData.put(Fields.SND_ID, vendor.getSenderId());
        requestData.put(Fields.STAMP, ""); //FIXME siia mingi jama
        requestData.put(Fields.AMOUNT, String.valueOf(paymentRequestParams.getAmount()));
        requestData.put(Fields.CURR, paymentRequestParams.getCurrency());
        requestData.put(Fields.REF, paymentRequestParams.getReferenceNumber());
        requestData.put(Fields.MSG, paymentRequestParams.getMessage());
        requestData.put(Fields.RETURN_URL, paymentRequestParams.getSuccessUri());
        requestData.put(Fields.CANCEL_URL, paymentRequestParams.getCancelUri());
        requestData.put(Fields.DATETIME, formatDate(new Date()));

        requestData.put(Fields.ENCODING, paymentRequestParams.getEncoding());
        requestData.put(Fields.LANG, paymentRequestParams.getLanguage());


        try {
            requestData.put(Fields.MAC, getRequestSignature(getMac(requestData, Services.PAYMENT_REQUEST)));
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | InvalidKeySpecException e) {
            throw new BanklinkException(e);
        }

        return requestData;

    }

    //FIXME: Encapuslate to Helper
    private RSAPrivateKey loadPrivateRSAKeyFromFile() throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory kFact = KeyFactory.getInstance("RSA");
        KeySpec ks = new PKCS8EncodedKeySpec(privateKey.getBytes());

        return (RSAPrivateKey)kFact.generatePrivate(ks);
    }

    protected String getRequestSignature(String mac) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException {
        RSAPrivateKey privKey = loadPrivateRSAKeyFromFile();
        Signature s = Signature.getInstance("SHA256withRSA");
        s.initSign(privKey);
        s.update(ByteBuffer.wrap(mac.getBytes()));
        byte[] signature = s.sign();

        return new String(Base64.getEncoder().encode(signature));
    }

    protected String getMac(Map<FieldDefinition, String> requestData, Services service) {
        String data = "";

        for (FieldDefinition field : Services.getFields(service)) {
            data += padMacParameter(field.getLength(), requestData.get(field));
        }

        return data;
    }


    private String formatDate(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        formatter.setTimeZone(tz);

        return formatter.format(date);
    }

    private String padMacParameter(int length, String value) {
        String prefix = "";
        int prefixLen = length - value.length();

        for (int i = 0; i < prefixLen; i++) {
            prefix += "0";
        }

        return prefix + value;
    }
}
