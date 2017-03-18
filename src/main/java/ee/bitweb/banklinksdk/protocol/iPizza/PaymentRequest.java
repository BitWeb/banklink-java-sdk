package ee.bitweb.banklinksdk.protocol.iPizza;

import ee.bitweb.banklinksdk.protocol.FieldDefinition;

import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public class PaymentRequest {

    protected Map<FieldDefinition, Object> requestData;

    public PaymentRequest(Map<FieldDefinition, Object> requestData) {
        this.requestData = requestData;
    }
}
