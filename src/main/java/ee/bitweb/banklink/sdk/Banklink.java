package ee.bitweb.banklink.sdk;

import com.sun.javafx.binding.Logging;
import ee.bitweb.banklink.sdk.exception.BanklinkException;
import ee.bitweb.banklink.sdk.params.AuthenticationRequestParams;
import ee.bitweb.banklink.sdk.params.PaymentRequestParams;
import ee.bitweb.banklink.sdk.protocol.FieldDefinition;
import ee.bitweb.banklink.sdk.protocol.Protocol;
import ee.bitweb.banklink.sdk.protocol.iPizza.Fields;
import ee.bitweb.banklink.sdk.protocol.iPizza.request.AuthenticationRequest;
import ee.bitweb.banklink.sdk.protocol.iPizza.request.PaymentRequest;
import ee.bitweb.banklink.sdk.protocol.iPizza.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

    protected String encoding = "UTF-8";
    protected String language = "EST";
    protected String currency = "EUR";

    protected Fields fields;

    private Log logger = LogFactory.getLog(Banklink.class);

    public Banklink(Protocol protocol) {
        this.protocol = protocol;
    }

    public Banklink(Protocol protocol, String encoding, String language, String currency) {
        this(protocol);
        this.encoding = encoding;
        this.language = language;
        this.currency = currency;
    }

    public String getPaymentRequestURI() {
        return !protocol.isTestMode() ? requestUri : testRequestUri;
    }

    public PaymentRequest prepareRequest(PaymentRequestParams paymentRequestParams) {
        logger.info("Preparing Payment request from data: " + LoggingHelper.parseObject(paymentRequestParams));

        logger.debug("Adding default values to params if not present in params");
        paymentRequestParams.setIfNotDefinedLanguage(language);
        paymentRequestParams.setIfNotDefinedEncoding(encoding);
        paymentRequestParams.setIfNotDefinedCurrency(currency);

        logger.debug("Preparing payment request");
        Map<FieldDefinition, String> requestData = protocol.preparePaymentRequest(paymentRequestParams);

        logger.debug("Preparing special fields");
        prepareSpecialFields(requestData);

        PaymentRequest request = new PaymentRequest(getPaymentRequestURI(), requestData);
        logger.debug("Prepared PaymentRequest object: " + LoggingHelper.parseObject(request));

        return request;
    }

    public String getAuthencationRequestURI() {
        return !protocol.isTestMode() ? requestUri : testRequestUri;
    }

    public AuthenticationRequest prepareRequest(AuthenticationRequestParams requestParams) {
        logger.info("Preparing Authentication request from data: " + LoggingHelper.parseObject(requestParams));

        logger.debug("Adding default values to params if not present in params");
        requestParams.setIfNotDefinedLanguage(language);
        requestParams.setIfNotDefinedEncoding(encoding);


        Map<FieldDefinition, String> requestData = protocol.prepareAuthenticationRequest(requestParams, getBankId());
        AuthenticationRequest request = new AuthenticationRequest(getAuthencationRequestURI(), requestData);
        logger.debug("Prepared AuthenticationRequest object: " + LoggingHelper.parseObject(request));

        return request;
    }

    protected abstract String getBankId();

    public Response handleResponse(Map<String, String> responseParams) {

        logger.info("Starting to handle response: " + LoggingHelper.parseObject(responseParams));

        logger.debug("Generating translated response");
        Map<FieldDefinition, String> translatedResponse = new HashMap<>();
        for (Map.Entry<String, String> responseParam : responseParams.entrySet()) {
            try {
                String translatedParam = convertToFieldParam(responseParam.getKey());
                Field f = fields.getClass().getField(translatedParam);
                FieldDefinition fieldDefinition = (FieldDefinition) f.get(null);
                translatedResponse.put(fieldDefinition, responseParam.getValue());
            } catch (NoSuchFieldException e) {
                //ignore, because invalid field, that can be ignored
                logger.warn("Bank has sent field " + responseParam.getKey() + " that does not exist in spec. Ignoring.");
            } catch (Exception e) {
                throw new BanklinkException("Response mapping failed", e);
            }
        }

        logger.debug("Generated translated response: " + LoggingHelper.parseObject(translatedResponse));

        return protocol.handleResponse(translatedResponse);
    }

    private String convertToFieldParam(String param) {
        return param.substring(3);
    }

    abstract protected Map<FieldDefinition, String> prepareSpecialFields(Map<FieldDefinition, String> requestData);
}
