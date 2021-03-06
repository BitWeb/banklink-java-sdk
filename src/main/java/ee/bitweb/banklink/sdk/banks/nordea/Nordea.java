package ee.bitweb.banklink.sdk.banks.nordea;

import ee.bitweb.banklink.sdk.Banklink;
import ee.bitweb.banklink.sdk.protocol.FieldDefinition;
import ee.bitweb.banklink.sdk.protocol.Protocol;

import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public class Nordea extends Banklink {

    private static final String BANKID = "NORDEA";

    protected String authenticationRequestUri = "https://netbank.nordea.com/pnbeid/eidp.jsp";
    protected String testAuthenticationRequestUri = "https://netbank.nordea.com/pnbeidtest/eidp.jsp";

    public Nordea(Protocol protocol) {
        super(protocol);
        super.requestUri = "https://netbank.nordea.com/pnbepay/epayp.jsp";
        super.testRequestUri = "https://netbank.nordea.com/pnbepaytest/epayp.jsp";
        super.fields = new NordeaFields();
    }

    public Nordea(Protocol protocol, String encoding, String language, String currency) {
        super(protocol, encoding, language, currency);
        super.requestUri = "https://netbank.nordea.com/pnbepay/epayp.jsp";
        super.testRequestUri = "https://netbank.nordea.com/pnbepaytest/epayp.jsp";
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

    public String getAuthencationRequestURI() {
        return !protocol.isTestMode() ? authenticationRequestUri : testAuthenticationRequestUri;
    }
}
