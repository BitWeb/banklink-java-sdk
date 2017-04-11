package ee.bitweb.banklink.sdk.protocol.iPizza;

import ee.bitweb.banklink.sdk.exception.BanklinkException;
import ee.bitweb.banklink.sdk.exception.IllegalArgumentException;
import ee.bitweb.banklink.sdk.params.AuthenticationRequestParams;
import ee.bitweb.banklink.sdk.params.PaymentRequestParams;
import ee.bitweb.banklink.sdk.protocol.FieldDefinition;
import ee.bitweb.banklink.sdk.protocol.Protocol;
import ee.bitweb.banklink.sdk.protocol.Vendor;
import ee.bitweb.banklink.sdk.protocol.iPizza.response.AuthenticationResponse;
import ee.bitweb.banklink.sdk.protocol.iPizza.response.PaymentResponse;
import ee.bitweb.banklink.sdk.protocol.iPizza.response.Response;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

/**
 * Created by tobre on 18/03/2017.
 */
public class iPizzaProtocol extends Protocol {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    protected String publicKey;
    protected String privateKey;

    protected Vendor vendor;

    protected String successUri;
    protected String cancelUri;

    public iPizzaProtocol(String publicKey, String privateKey, Vendor vendor) {
        if (publicKey == null) {
            throw new IllegalArgumentException("Public key not set");
        }

        if (privateKey == null) {
            throw new IllegalArgumentException("Private key not set");
        }

        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.vendor = vendor;
    }

    public iPizzaProtocol(String publicKey, String privateKey, Vendor vendor, String successUri, String cancelUri) {
        this(publicKey, privateKey, vendor);
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
            requestData.put(Fields.MAC, Mac.sign(getMac(requestData), privateKey));
        } catch (GeneralSecurityException | IOException e) {
            throw new BanklinkException("MAC signing failed, private key error", e);
        }

        return requestData;

    }

    public Map<FieldDefinition, String> prepareAuthenticationRequest(AuthenticationRequestParams requestParams, String bankId) {
        Map<FieldDefinition, String> requestData = new HashMap<>();

        requestData.put(Fields.SERVICE, Services.AUTHENTICATE_REQUEST.code);
        requestData.put(Fields.VERSION, version);
        requestData.put(Fields.SND_ID, vendor.getSenderId());
        requestData.put(Fields.REC_ID, bankId);
        requestData.put(Fields.NONCE, generateNonce());
        requestData.put(Fields.RETURN_URL, requestParams.getSuccessUri() == null ? successUri : requestParams.getSuccessUri());
        requestData.put(Fields.DATETIME, formatDate(new DateTime()));
        requestData.put(Fields.ENCODING, requestParams.getEncoding());
        requestData.put(Fields.RID, "");

        try {
            requestData.put(Fields.MAC, Mac.sign(getMac(requestData), privateKey));
        } catch (GeneralSecurityException | IOException e) {
            throw new BanklinkException("MAC signing failed, private key error", e);
        }

        return requestData;
    }

    private String generateNonce() {
        return UUID.randomUUID().toString();
    }

    protected String getMac(Map<FieldDefinition, String> requestData) {
        Services service = getService(requestData);

        String data = "";
        for (FieldDefinition field : Services.getFields(service)) {
            data += padMacParameter(requestData.get(field));
        }

        return data;
    }

    private Services getService(Map<FieldDefinition, String> requestData) {
        return Services.getByCode(requestData.get(Fields.SERVICE));
    }

    private String formatDate(DateTime date) {
        DateTimeFormatter df = DateTimeFormat.forPattern(DATE_FORMAT);

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
    public Response handleResponse(Map<FieldDefinition, String> responseParams) {
        String mac = getMac(responseParams);

        Boolean isMacVerified = false;
        try {
            isMacVerified = Mac.verify(mac, responseParams.get(Fields.MAC), publicKey);
        } catch (GeneralSecurityException | IOException e) {
            throw new BanklinkException("MAC verification failed, certification error", e);
        }

        if (!isMacVerified) {
            throw new BanklinkException("MAC parameter not verified. Bank has sent wrong parameters.");
        }

        Services service = getService(responseParams);
        if (service == Services.PAYMENT_SUCCESS || service == Services.PAYMENT_ERROR) {
            return handlePaymentResponse(responseParams);
        } else if (service == Services.AUTHENTICATE_SUCCESS) {
            return handleAuthenticationResponse(responseParams);
        }

        throw new BanklinkException("Method response not supported");
    }

    protected PaymentResponse handlePaymentResponse(Map<FieldDefinition, String> responseParams) {
        Boolean isAuto = false;
        if (responseParams.get(Fields.AUTO).equals("Y")) {
            isAuto = true;
        }
        DateTimeFormatter df = DateTimeFormat.forPattern(DATE_FORMAT);
        DateTime transactionTimestamp = df.parseDateTime(responseParams.get(Fields.T_DATETIME));

        return new PaymentResponse(
                responseParams.get(Fields.STAMP),
                responseParams.get(Fields.T_NO),
                responseParams.get(Fields.SND_ID),
                isAuto,
                transactionTimestamp
        );
    }

    protected AuthenticationResponse handleAuthenticationResponse(Map<FieldDefinition, String> responseParams) {
        DateTimeFormatter df = DateTimeFormat.forPattern(DATE_FORMAT);
        DateTime timestamp = df.parseDateTime(responseParams.get(Fields.DATETIME));

        return new AuthenticationResponse(
                responseParams.get(Fields.USER_NAME),
                responseParams.get(Fields.USER_ID),
                timestamp,
                responseParams.get(Fields.OTHER),
                responseParams.get(Fields.SND_ID),
                responseParams.get(Fields.COUNTRY)
        );
    }
}
