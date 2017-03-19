package ee.bitweb.banklinksdk.protocol;

import ee.bitweb.banklinksdk.protocol.iPizza.Response;
import ee.bitweb.banklinksdk.request.AuthenticationRequestParams;
import ee.bitweb.banklinksdk.request.PaymentRequestParams;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public abstract class Protocol {

    protected Boolean isTestMode = false;
    protected String version = "008";

    public Boolean isTestMode() {
        return isTestMode;
    }

    public void setTestMode(Boolean testMode) {
        isTestMode = testMode;
    }

    abstract public Map<FieldDefinition, String> preparePaymentRequest(PaymentRequestParams paymentRequestParams);

    abstract public Map<FieldDefinition, String> prepareAuthenticationRequest(AuthenticationRequestParams requestParams);


    public abstract Response handleResponse(Map<FieldDefinition, String> responseParams);
}
