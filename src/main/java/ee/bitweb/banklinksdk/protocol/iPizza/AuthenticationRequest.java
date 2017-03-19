package ee.bitweb.banklinksdk.protocol.iPizza;


import ee.bitweb.banklinksdk.protocol.FieldDefinition;

import java.util.Map;

public class AuthenticationRequest {


    protected String bankUri;
    protected Map<FieldDefinition, String> requestData;

    public AuthenticationRequest(String uri, Map<FieldDefinition, String> requestData) {
        this.bankUri = uri;
        this.requestData = requestData;
    }

}
