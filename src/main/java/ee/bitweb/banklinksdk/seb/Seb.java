package ee.bitweb.banklinksdk.seb;

import ee.bitweb.banklinksdk.Banklink;
import ee.bitweb.banklinksdk.protocol.FieldDefinition;
import ee.bitweb.banklinksdk.protocol.Protocol;
import ee.bitweb.banklinksdk.protocol.iPizza.Fields;
import ee.bitweb.banklinksdk.protocol.iPizza.Fields2;

import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public class Seb extends Banklink {

    protected String requestUri = ""; //TODO: Meie libist võtta
    protected String testRequestUri = ""; //TODO: sebi specist võtta

    public Seb(Protocol protocol) {
        super(protocol);
        super.fields = new SebFields();
    }

    public Seb(Protocol protocol, String successUri, String cancelUri) {
        super(protocol, successUri, cancelUri);
        super.fields = new SebFields();
    }

    public Seb(Protocol protocol, String successUri, String cancelUri, String encoding, String language, String currency) {
        super(protocol, successUri, cancelUri, encoding, language, currency);
        super.fields = new SebFields();
    }

    @Override
    protected Map<FieldDefinition, Object> prepareSpecialFields(Map<FieldDefinition, Object> requestData) {
        return null;
    }

}
