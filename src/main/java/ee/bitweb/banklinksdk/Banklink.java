package ee.bitweb.banklinksdk;

import ee.bitweb.banklinksdk.protocol.Protocol;
import ee.bitweb.banklinksdk.protocol.iPizza.PaymentRequest;
import ee.bitweb.banklinksdk.request.PaymentRequestParams;

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

    public Banklink(Protocol protocol, String successUri, String cancelUri) {
        this.protocol = protocol;
        this.successUri = successUri;
        this.cancelUri = cancelUri;
    }

    public void makeRequest() {

    }

    public PaymentRequest preparePaymentRequest(PaymentRequestParams paymentRequestParams) {
        Map<String, Object> requestData = new HashMap<String, Object>();

        //requestData.putAll(protocol.prepareCommonParameters());

        protocol.prepareRequest(requestData);
        prepareSpecialFields(requestData);



        return new PaymentRequest();

    }

    public void prepareAuthenticationRequest() {

    }

    abstract void prepareSpecialFields(Map<String, Object> requestData);

}
