package ee.bitweb.banklink.sdk.banks.krediidipank;

import ee.bitweb.banklink.sdk.protocol.FieldDefinition;
import ee.bitweb.banklink.sdk.protocol.iPizza.Fields;
import ee.bitweb.banklink.sdk.Banklink;
import ee.bitweb.banklink.sdk.protocol.Protocol;

import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public class Krediidipank extends Banklink {

    private static final String BANKID = "KREP";

    protected String authenticationRequestUri = "https://i-pank.cooppank.ee/auth";
    protected String testAuthenticationRequestUri = "https://secure.cooppank.ee/auth";


    public Krediidipank(Protocol protocol) {
        super(protocol);
        super.requestUri = "https://i-pank.cooppank.ee/pay";
        super.testRequestUri = "https://secure.cooppank.ee/pay";
        super.fields = new Fields();
    }

    public Krediidipank(Protocol protocol, String encoding, String language, String currency) {
        super(protocol, encoding, language, currency);
        super.requestUri = "https://i-pank.cooppank.ee/pay";
        super.testRequestUri = "https://secure.cooppank.ee/pay";
        super.fields = new Fields();
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
