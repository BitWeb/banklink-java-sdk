package ee.bitweb.banklinksdk.protocol.iPizza;

import ee.bitweb.banklinksdk.protocol.FieldDefinition;

import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public class PaymentResponse extends Response {

    public PaymentResponse(Map<FieldDefinition, String> responseData) {
        super(responseData);
    }
}
