import ee.bitweb.banklinksdk.Banklink;
import ee.bitweb.banklinksdk.seb.Seb;
import ee.bitweb.banklinksdk.protocol.Vendor;
import ee.bitweb.banklinksdk.protocol.Protocol;
import ee.bitweb.banklinksdk.protocol.iPizza.PaymentRequest;
import ee.bitweb.banklinksdk.protocol.iPizza.iPizzaProtocol;
import ee.bitweb.banklinksdk.request.PaymentRequestParams;
import ee.bitweb.banklinksdk.seb.SebFields;

/**
 * Created by tobre on 18/03/2017.
 */
public class Main {

    public static void main(String args[]) {

        Protocol protocol = new iPizzaProtocol(
                "public",
                "private",
                new Vendor("sender", "name", "account"),
                "http://localhost/return",
                "http://localhost/return"
        );
        Banklink seb = new Seb(protocol);

        PaymentRequest paymentRequest = seb.preparePaymentRequest(new PaymentRequestParams(12.0, "Laheee", "Minu Vitunumber"));

        String html = paymentRequest.createRequestHtml();

        System.out.println(html);



        seb.prepareAuthenticationRequest();

    }

}
