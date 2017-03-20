package ee.bitweb.banklinksdk.protocol.iPizza;


import ee.bitweb.banklinksdk.protocol.FieldDefinition;

import java.util.Map;

public class AuthenticationRequest extends Request {

    public AuthenticationRequest(String uri, Map<FieldDefinition, String> requestData) {
        super(uri, requestData);
    }
}
