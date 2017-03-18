package ee.bitweb.banklinksdk;

import ee.bitweb.banklinksdk.protocol.Protocol;

import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public class Seb extends Banklink {

    protected String requestUri = ""; //TODO: Meie libist võtta
    protected String testRequestUri = ""; //TODO: sebi specist võtta


    public Seb(Protocol protocol) {
        super(protocol);
    }

    @Override
    void prepareSpecialFields(Map<String, Object> requestData) {

    }
}
