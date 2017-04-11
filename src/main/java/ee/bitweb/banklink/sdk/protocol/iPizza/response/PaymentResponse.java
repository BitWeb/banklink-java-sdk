package ee.bitweb.banklink.sdk.protocol.iPizza.response;

import org.joda.time.DateTime;

/**
 * Created by tobre on 18/03/2017.
 */
public class PaymentResponse extends Response {

    protected String transactionNumber;
    protected String senderId;
    protected Boolean isAuto;
    protected DateTime transactionTimestamp;
    protected String bankTransactionNumber;

    public PaymentResponse(String transactionNumber, String bankTransactionNumber, String senderId, Boolean isAuto, DateTime transactionTimestamp) {
        this.transactionNumber = transactionNumber;
        this.bankTransactionNumber = bankTransactionNumber;
        this.senderId = senderId;
        this.isAuto = isAuto;
        this.transactionTimestamp = transactionTimestamp;

    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public String getBankTransactionNumber() {
        return bankTransactionNumber;
    }

    public String getSenderId() {
        return senderId;
    }

    public Boolean isAuto() {
        return isAuto;
    }

    public DateTime getTransactionTimestamp() {
        return transactionTimestamp;
    }
}
