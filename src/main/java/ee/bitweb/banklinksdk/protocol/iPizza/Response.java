package ee.bitweb.banklinksdk.protocol.iPizza;

import ee.bitweb.banklinksdk.protocol.FieldDefinition;

import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public class Response {

    protected Map<FieldDefinition, String> responseData;

    public Response(Map<FieldDefinition, String> responseData) {
        this.responseData = responseData;
    }

    public Map<FieldDefinition, String> getResponseData() {
        return responseData;
    }
}
