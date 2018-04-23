package ee.bitweb.banklink.sdk.banks.danske;

import ee.bitweb.banklink.sdk.protocol.iPizza.Fields;
import ee.bitweb.banklink.sdk.Banklink;
import ee.bitweb.banklink.sdk.protocol.FieldDefinition;
import ee.bitweb.banklink.sdk.protocol.Protocol;

import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public class Danske extends Banklink {

    private static final String BANKID = "SAMPOPANK";

    public Danske(Protocol protocol) {
        super(protocol);
        super.requestUri = "https://e.danskebank.ee/ib/site/ibpay/login";
        super.testRequestUri = "https://pangalink.bitweb.ee/banklink/sampo";
        super.fields = new Fields();
    }

    public Danske(Protocol protocol, String encoding, String language, String currency) {
        super(protocol, encoding, language, currency);
        super.requestUri = "https://e.danskebank.ee/ib/site/ibpay/login";
        super.testRequestUri = "https://pangalink.bitweb.ee/banklink/sampo";
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
