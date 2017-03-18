package ee.bitweb.banklinksdk.protocol;

/**
 * Created by tobre on 18/03/2017.
 */
public class Vendor {

    protected String senderId;
    protected String name;
    protected String accountNumber;

    public Vendor(String senderId, String name, String accountNumber) {
        this.senderId = senderId;
        this.name = name;
        this.accountNumber = accountNumber;
    }
}
