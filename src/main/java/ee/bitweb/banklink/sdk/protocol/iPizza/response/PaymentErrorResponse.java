package ee.bitweb.banklink.sdk.protocol.iPizza.response;

public class PaymentErrorResponse extends Response {

    protected String transactionNumber;
    protected Boolean isAuto;

    public PaymentErrorResponse (String transactionNumber, String senderId, Boolean isAuto) {
        this.transactionNumber = transactionNumber;
        this.senderId = senderId;
        this.isAuto = isAuto;

    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public Boolean getAuto() {
        return isAuto;
    }
}
