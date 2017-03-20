package ee.bitweb.banklink.sdk.protocol.iPizza.request;


import ee.bitweb.banklink.sdk.protocol.FieldDefinition;

import java.util.Map;

public class AuthenticationRequest extends Request {

    public AuthenticationRequest(String uri, Map<FieldDefinition, String> requestData) {
        super(uri, requestData);
    }
}
