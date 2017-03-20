package ee.bitweb.banklinksdk.banks.krediidipank;

import ee.bitweb.banklinksdk.Banklink;
import ee.bitweb.banklinksdk.protocol.FieldDefinition;
import ee.bitweb.banklinksdk.protocol.Protocol;
import ee.bitweb.banklinksdk.protocol.iPizza.Fields;

import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public class Krediidipank extends Banklink {

    private static String BANKID = "Krediidipank";

    public Krediidipank(Protocol protocol) {
        super(protocol);
        super.requestUri = "https://i-pank.krediidipank.ee/teller/autendi";
        super.testRequestUri = "https://pangalink.bitweb.ee/banklink/krediidipank";
        super.fields = new Fields();
    }

    public Krediidipank(Protocol protocol, String successUri, String cancelUri) {
        super(protocol, successUri, cancelUri);
        super.fields = new Fields();
    }

    public Krediidipank(Protocol protocol, String successUri, String cancelUri, String encoding, String language, String currency) {
        super(protocol, successUri, cancelUri, encoding, language, currency);
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
}
