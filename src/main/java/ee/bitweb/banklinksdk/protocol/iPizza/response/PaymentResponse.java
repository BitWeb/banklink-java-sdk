package ee.bitweb.banklinksdk.protocol.iPizza.response;

import org.joda.time.DateTime;

/**
 * Created by tobre on 18/03/2017.
 */
public class PaymentResponse extends Response {

    protected String transactionNumber;
    protected String senderId;
    protected Boolean isAuto;
    protected DateTime transactionTimestamp;

    public PaymentResponse(String transactionNumber, String senderId, Boolean isAuto, DateTime transactionTimestamp) {
        this.transactionNumber = transactionNumber;
        this.senderId = senderId;
        this.isAuto = isAuto;
        this.transactionTimestamp = transactionTimestamp;
    }

    public String getTransactionNumber() {
        return transactionNumber;
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
