import ee.bitweb.banklinksdk.Banklink;
import ee.bitweb.banklinksdk.Seb;
import ee.bitweb.banklinksdk.protocol.Vendor;
import ee.bitweb.banklinksdk.protocol.Protocol;
import ee.bitweb.banklinksdk.protocol.iPizza.Fields;
import ee.bitweb.banklinksdk.protocol.iPizza.PaymentRequest;
import ee.bitweb.banklinksdk.protocol.iPizza.iPizzaProtocol;
import ee.bitweb.banklinksdk.request.PaymentRequestParams;

/**
 * Created by tobre on 18/03/2017.
 */
public class Main {

    public static void main(String args[]) {

        Protocol protocol = new iPizzaProtocol(
                "public",
                "private",
                new Vendor("sender", "name", "account")
        );
        Banklink seb = new Seb(protocol, successUri, cancelUri);

        PaymentRequest paymentRequest = seb.preparePaymentRequest(new PaymentRequestParams(12.0, "Laheee"));

        paymentRequest.getFildsJson();
        paymentRequest.getAmount();

       // Fields.SERVICE.length;



        seb.prepareAuthenticationRequest();

    }

}
