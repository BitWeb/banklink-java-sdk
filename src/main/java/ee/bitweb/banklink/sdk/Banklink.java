package ee.bitweb.banklink.sdk;

import ee.bitweb.banklink.sdk.exception.BanklinkException;
import ee.bitweb.banklink.sdk.params.AuthenticationRequestParams;
import ee.bitweb.banklink.sdk.params.PaymentRequestParams;
import ee.bitweb.banklink.sdk.protocol.FieldDefinition;
import ee.bitweb.banklink.sdk.protocol.iPizza.Fields;
import ee.bitweb.banklink.sdk.protocol.iPizza.request.PaymentRequest;
import ee.bitweb.banklink.sdk.protocol.iPizza.response.Response;
import ee.bitweb.banklink.sdk.protocol.Protocol;
import ee.bitweb.banklink.sdk.protocol.iPizza.request.AuthenticationRequest;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public abstract class Banklink {

    protected Protocol protocol;

    protected String requestUri;
    protected String testRequestUri;

    protected String successUri;
    protected String cancelUri;
    protected String encoding = "UTF-8";
    protected String language = "EST";
    protected String currency = "EUR";

    protected Fields fields;

    public Banklink(Protocol protocol) {
        this.protocol = protocol;
    }

    public Banklink(Protocol protocol, String successUri, String cancelUri) {
        this(protocol);
        this.successUri = successUri;
        this.cancelUri = cancelUri;
    }

    public Banklink(Protocol protocol, String successUri, String cancelUri, String encoding, String language, String currency) {
        this(protocol, successUri, cancelUri);
        this.encoding = encoding;
        this.language = language;
        this.currency = currency;
    }

    public PaymentRequest prepareRequest(PaymentRequestParams paymentRequestParams) {
        paymentRequestParams.setIfNotDefinedLanguage(language);
        paymentRequestParams.setIfNotDefinedEncoding(encoding);
        paymentRequestParams.setIfNotDefinedCurrency(currency);

        Map<FieldDefinition, String> requestData = protocol.preparePaymentRequest(paymentRequestParams);

        prepareSpecialFields(requestData);

        return new PaymentRequest(isTestMode(), requestData);
    }

    private String isTestMode() {
        return !protocol.isTestMode() ? requestUri : testRequestUri;
    }

    public AuthenticationRequest prepareRequest(AuthenticationRequestParams requestParams) {
        requestParams.setIfNotDefinedLanguage(language);
        requestParams.setIfNotDefinedEncoding(encoding);

        Map<FieldDefinition, String> requestData = protocol.prepareAuthenticationRequest(requestParams, getBankId());

        return new AuthenticationRequest(isTestMode(), requestData);
    }

    protected abstract String getBankId();

    public Response handleResponse(Map<String, String> responseParams) {

        Map<FieldDefinition, String> translatedResponse = new HashMap<>();
        for (Map.Entry<String, String> responseParam : responseParams.entrySet()) {
            try {
                String translatedParam = convertToFieldParam(responseParam.getKey());
                Field f = fields.getClass().getField(translatedParam);
                FieldDefinition fieldDefinition = (FieldDefinition) f.get(null);
                translatedResponse.put(fieldDefinition, responseParam.getValue());
            } catch (Exception e) {
                throw new BanklinkException("Response mapping failed");
            }
        }

        return protocol.handleResponse(translatedResponse);
    }

    private String convertToFieldParam(String param) {
        return param.substring(3);
    }

    abstract protected Map<FieldDefinition, String> prepareSpecialFields(Map<FieldDefinition, String> requestData);
}
