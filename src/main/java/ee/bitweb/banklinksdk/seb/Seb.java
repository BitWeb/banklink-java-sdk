package ee.bitweb.banklinksdk.seb;

import ee.bitweb.banklinksdk.Banklink;
import ee.bitweb.banklinksdk.protocol.FieldDefinition;
import ee.bitweb.banklinksdk.protocol.Protocol;

import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public class Seb extends Banklink {


    public Seb(Protocol protocol) {
        super(protocol);
        super.requestUri = "https://www.seb.ee/cgi-bin/unet3.sh/un3min.r";
        super.testRequestUri = "https://www.seb.ee/cgi-bin/dv.sh/ipank.r";
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
    protected Map<FieldDefinition, String> prepareSpecialFields(Map<FieldDefinition, String> requestData) {
        return null;
    }

}
