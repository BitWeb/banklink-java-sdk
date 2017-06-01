package ee.bitweb.banklink.sdk.protocol.iPizza;

import ee.bitweb.banklink.sdk.LoggingHelper;
import ee.bitweb.banklink.sdk.exception.BanklinkException;
import ee.bitweb.banklink.sdk.exception.IllegalArgumentException;
import ee.bitweb.banklink.sdk.params.AuthenticationRequestParams;
import ee.bitweb.banklink.sdk.params.PaymentRequestParams;
import ee.bitweb.banklink.sdk.protocol.FieldDefinition;
import ee.bitweb.banklink.sdk.protocol.Protocol;
import ee.bitweb.banklink.sdk.protocol.Vendor;
import ee.bitweb.banklink.sdk.protocol.iPizza.response.AuthenticationResponse;
import ee.bitweb.banklink.sdk.protocol.iPizza.response.PaymentErrorResponse;
import ee.bitweb.banklink.sdk.protocol.iPizza.response.PaymentResponse;
import ee.bitweb.banklink.sdk.protocol.iPizza.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

/**
 * Created by tobre on 18/03/2017.
 */
public class iPizzaProtocol extends Protocol {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    protected final Log logger = LogFactory.getLog(Mac.class);
    protected PrivateKey privateKey;
    protected PublicKey publicKey;

    protected Vendor vendor;

    public iPizzaProtocol(PublicKey publicKey, PrivateKey privateKey, Vendor vendor) {

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

    public iPizzaProtocol(String publicKeyCertString, String privateKeyStr, Vendor vendor) throws IOException, GeneralSecurityException {
        this(Mac.getPublicKeyFromCertificateString(publicKeyCertString), Mac.getPrivateKey(privateKeyStr), vendor);
    }

    @Override
    public Map<FieldDefinition, String> preparePaymentRequest(PaymentRequestParams paymentRequestParams) {
        logger.info("Preparing payment request: " + LoggingHelper.parseObject(paymentRequestParams));
        Map<FieldDefinition, String> requestData = new HashMap<>();
        logger.debug("Putting together request data");

        requestData.put(Fields.SERVICE, Services.PAYMENT_REQUEST.code);
        requestData.put(Fields.VERSION, version);
        requestData.put(Fields.SND_ID, vendor.getSenderId());
        requestData.put(Fields.STAMP, paymentRequestParams.getTransactionId());
        requestData.put(Fields.AMOUNT, String.valueOf(paymentRequestParams.getAmount()));
        requestData.put(Fields.CURR, paymentRequestParams.getCurrency());
        requestData.put(Fields.REF, paymentRequestParams.getReferenceNumber());
        requestData.put(Fields.MSG, paymentRequestParams.getMessage());
        requestData.put(Fields.RETURN_URL, paymentRequestParams.getSuccessUri());
        requestData.put(Fields.CANCEL_URL, paymentRequestParams.getCancelUri());
        requestData.put(Fields.DATETIME, formatDate(new DateTime()));
        requestData.put(Fields.ENCODING, paymentRequestParams.getEncoding());
        requestData.put(Fields.LANG, paymentRequestParams.getLanguage());

        try {
            logger.debug("Computing and adding mac signature");
            requestData.put(Fields.MAC, Mac.sign(getMac(requestData), privateKey));
        } catch (Exception e) {
            logger.error("An error happened during MAC signature generation", e);
            throw new BanklinkException("MAC signing failed, private key error", e);
        }

        logger.debug("Compiled request data: " + LoggingHelper.parseObject(requestData));

        return requestData;

    }

    public Map<FieldDefinition, String> prepareAuthenticationRequest(AuthenticationRequestParams requestParams, String bankId) {
        logger.info("Preparing authentication request for bank " + bankId + " : " + LoggingHelper.parseObject(requestParams));
        Map<FieldDefinition, String> requestData = new HashMap<>();
        logger.debug("Putting together request data");

        requestData.put(Fields.SERVICE, Services.AUTHENTICATE_REQUEST.code);
        requestData.put(Fields.VERSION, version);
        requestData.put(Fields.SND_ID, vendor.getSenderId());
        requestData.put(Fields.REC_ID, bankId);
        requestData.put(Fields.NONCE, generateNonce());
        requestData.put(Fields.RETURN_URL, requestParams.getReturnUri());
        requestData.put(Fields.DATETIME, formatDate(new DateTime()));
        requestData.put(Fields.ENCODING, requestParams.getEncoding());
        requestData.put(Fields.RID, "");
        requestData.put(Fields.LANG, requestParams.getLanguage());

        try {
            logger.debug("Computing and adding MAC signature");
            requestData.put(Fields.MAC, Mac.sign(getMac(requestData), privateKey));
        } catch (Exception e) {
            logger.error("An error happened during MAC signature generation", e);
            throw new BanklinkException("MAC signing failed, private key error", e);
        }

        logger.debug("Compiled request data: " + LoggingHelper.parseObject(requestData));

        return requestData;
    }

    private String generateNonce() {
        logger.info("Generating nonce");

        return UUID.randomUUID().toString();
    }

    protected String getMac(Map<FieldDefinition, String> requestData) {
        logger.info("Generating MAC value");
        Services service = getService(requestData);
        logger.debug("Got a service: " + service.code);
        String data = "";
        for (FieldDefinition field : Services.getFields(service)) {
            data += padMacParameter(requestData.get(field));
        }

        logger.debug("Generated MAC value: " + data);

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

        logger.debug("Padded value " + value + " to " + length + value);

        return length + value;
    }

    @Override
    public Response handleResponse(Map<FieldDefinition, String> responseParams) {
        logger.info("Handling response");

        String mac = getMac(responseParams);
        logger.debug("Compiled a MAC value: " + mac);

        Boolean isMacVerified = false;
        logger.debug("Starting to verify MAC signature: " + responseParams.get(Fields.MAC));
        try {
            isMacVerified = Mac.verify(mac, responseParams.get(Fields.MAC), publicKey);
        } catch (GeneralSecurityException | IOException e) {
            logger.error("An error occured while MAC verification", e);
            throw new BanklinkException("MAC verification failed, certification error", e);
        }

        if (!isMacVerified) {
            logger.warn("MAC signature was not verified");
            throw new BanklinkException("MAC signature not verified. Data might have been tampered with.");
        }

        Services service = getService(responseParams);

        if (service == null) {

            logger.warn ("Service not recognized " + responseParams.get(Fields.SERVICE));
            throw new BanklinkException("Service not recognized " + responseParams.get(Fields.SERVICE));
        }

        logger.debug("Recognized service: " + service.code);

        if (service == Services.PAYMENT_SUCCESS) return handlePaymentResponse(responseParams);
        if (service == Services.PAYMENT_ERROR) return handlePaymentErrorResponse(responseParams);
        if (service == Services.AUTHENTICATE_SUCCESS) return handleAuthenticationResponse(responseParams);

        logger.warn("Service " + service.code + " not supported");

        throw new BanklinkException("Service " + service.code + " not supported");
    }

    protected PaymentResponse handlePaymentResponse(Map<FieldDefinition, String> responseParams) {

        logger.info("Handling payment response");
        logger.debug("Parsing date from params to date object");

        DateTimeFormatter df = DateTimeFormat.forPattern(DATE_FORMAT);
        DateTime transactionTimestamp = df.parseDateTime(responseParams.get(Fields.T_DATETIME));

        PaymentResponse response = new PaymentResponse(
            responseParams.get(Fields.STAMP),
            responseParams.get(Fields.T_NO),
            responseParams.get(Fields.SND_ID),
            isAuto(responseParams),
            transactionTimestamp
        );

        logger.debug("Putting together PaymentResponse object : " + LoggingHelper.parseObject(response));

        return response;
    }

    protected Boolean isAuto (Map<FieldDefinition, String> responseParams) {
        return responseParams.get(Fields.AUTO).equals("Y");
    }

    protected PaymentErrorResponse handlePaymentErrorResponse(Map<FieldDefinition, String> responseParams) {

        logger.info("Handling error response");

        PaymentErrorResponse response = new PaymentErrorResponse(
            responseParams.get(Fields.STAMP),
            responseParams.get(Fields.SND_ID),
            isAuto(responseParams)
        );

        logger.debug("Putting together PaymentErrorResponse object: " + LoggingHelper.parseObject(response));

        return response;
    }

    protected AuthenticationResponse handleAuthenticationResponse(Map<FieldDefinition, String> responseParams) {

        logger.info("Handling payment response");
        logger.debug("Parsing date from params to date object");

        DateTimeFormatter df = DateTimeFormat.forPattern(DATE_FORMAT);
        DateTime timestamp = df.parseDateTime(responseParams.get(Fields.DATETIME));

        AuthenticationResponse response = new AuthenticationResponse(
            responseParams.get(Fields.USER_NAME),
            responseParams.get(Fields.USER_ID),
            timestamp,
            responseParams.get(Fields.OTHER),
            responseParams.get(Fields.SND_ID),
            responseParams.get(Fields.COUNTRY)
        );

        logger.debug("Putting together AuthenticationResponse object: " + LoggingHelper.parseObject(response));

        return response;
    }
}
