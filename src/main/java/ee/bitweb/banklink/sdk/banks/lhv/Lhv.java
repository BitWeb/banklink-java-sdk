package ee.bitweb.banklink.sdk.banks.lhv;

import ee.bitweb.banklink.sdk.protocol.iPizza.Fields;
import ee.bitweb.banklink.sdk.Banklink;
import ee.bitweb.banklink.sdk.protocol.FieldDefinition;
import ee.bitweb.banklink.sdk.protocol.Protocol;

import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public class Lhv extends Banklink {

    private static final String BANKID = "LHV";

    public Lhv(Protocol protocol) {
        super(protocol);
        super.requestUri = "https://www.lhv.ee/banklink";
        super.testRequestUri = "https://pangalink.bitweb.ee/banklink/seb";
        super.fields = new Fields();
    }

    public Lhv(Protocol protocol, String successUri, String cancelUri) {
        super(protocol, successUri, cancelUri);
        super.fields = new Fields();
    }

    public Lhv(Protocol protocol, String successUri, String cancelUri, String encoding, String language, String currency) {
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
