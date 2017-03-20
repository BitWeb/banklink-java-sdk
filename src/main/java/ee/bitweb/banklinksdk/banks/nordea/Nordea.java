package ee.bitweb.banklinksdk.banks.nordea;

import ee.bitweb.banklinksdk.Banklink;
import ee.bitweb.banklinksdk.protocol.FieldDefinition;
import ee.bitweb.banklinksdk.protocol.Protocol;

import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public class Nordea extends Banklink {

    private static String BANKID = "NORDEA";

    protected String testAuthenticationRequestUri = "https://netbank.nordea.com/pnbepaytest/epayp.jsp";

    public Nordea(Protocol protocol) {
        super(protocol);
        super.requestUri = "https://netbank.nordea.com/pnbepay/epayp.jsp";
        super.testRequestUri = "https://netbank.nordea.com/pnbepaytest/epayp.jsp";
        super.fields = new NordeaFields();
    }

    public Nordea(Protocol protocol, String successUri, String cancelUri) {
        super(protocol, successUri, cancelUri);
        super.fields = new NordeaFields();
    }

    public Nordea(Protocol protocol, String successUri, String cancelUri, String encoding, String language, String currency) {
        super(protocol, successUri, cancelUri, encoding, language, currency);
        super.fields = new NordeaFields();
    }

    @Override
    protected Map<FieldDefinition, String> prepareSpecialFields(Map<FieldDefinition, String> requestData) {
        return requestData;
    }

    @Override
    protected String getBankId() {
        return BANKID;
    }
}
