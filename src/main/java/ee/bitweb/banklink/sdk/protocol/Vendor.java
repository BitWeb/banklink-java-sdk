package ee.bitweb.banklink.sdk.protocol;

/**
 * Created by tobre on 18/03/2017.
 */
public class Vendor {

    protected String senderId;
    protected String name;
    protected String accountNumber;

    public Vendor(String senderId, String name) {
        this.senderId = senderId;
        this.name = name;
    }

    public Vendor(String senderId, String name, String accountNumber) {
        this(senderId, name);
        this.accountNumber = accountNumber;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
