package ee.bitweb.banklinksdk.request;

/**
 * Created by tobre on 18/03/2017.
 */
public class PaymentRequestParams {

    protected Double amount;
    protected String message;
    protected String referenceNumber;
    protected String language;
    protected String currency;
    protected String successUri;
    protected String cancelUri;

    public PaymentRequestParams(Double amount, String message) {
        this.amount = amount;
        this.message = message;
    }

    public PaymentRequestParams(Double amount, String message, String referenceNumber) {
        this(amount, message);
        this.referenceNumber = referenceNumber;
    }

    public PaymentRequestParams(Double amount, String message, String referenceNumber, String language, String currency) {
        this(amount, message, referenceNumber);
        this.language = language;
        this.currency = currency;
    }

    public PaymentRequestParams(Double amount, String message, String referenceNumber, String language, String currency, String successUri, String cancelUri) {
        this(amount, message, referenceNumber, language, currency);
        this.successUri = successUri;
        this.cancelUri = cancelUri;
    }
}
