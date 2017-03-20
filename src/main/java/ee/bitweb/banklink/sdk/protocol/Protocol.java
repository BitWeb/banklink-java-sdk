package ee.bitweb.banklink.sdk.protocol;

import ee.bitweb.banklink.sdk.params.AuthenticationRequestParams;
import ee.bitweb.banklink.sdk.params.PaymentRequestParams;
import ee.bitweb.banklink.sdk.protocol.iPizza.response.Response;

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

    abstract public Map<FieldDefinition, String> prepareAuthenticationRequest(AuthenticationRequestParams requestParams, String bankId);

    public abstract Response handleResponse(Map<FieldDefinition, String> responseParams);
}
