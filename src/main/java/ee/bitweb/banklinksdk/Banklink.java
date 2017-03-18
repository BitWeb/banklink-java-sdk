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
    protected String encoding = "UTF-8";
    protected String language = "EST";
    protected String currency = "EUR";

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


    public PaymentRequest preparePaymentRequest(PaymentRequestParams paymentRequestParams) {
        paymentRequestParams.setIfNotDefinedLanguage(language);
        paymentRequestParams.setIfNotDefinedEncoding(encoding);
        paymentRequestParams.setIfNotDefinedCurrency(currency);


        Map<String, Object> requestData = new HashMap<String, Object>();


        protocol.preparePaymentRequest(paymentRequestParams);
        prepareSpecialFields(requestData);



        return new PaymentRequest();

    }

    public void prepareAuthenticationRequest() {

    }

    abstract void prepareSpecialFields(Map<String, Object> requestData);

}
