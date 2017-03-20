package ee.bitweb.banklinksdk.protocol.iPizza;

import ee.bitweb.banklinksdk.protocol.FieldDefinition;

import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public class PaymentRequest extends Request {

    public PaymentRequest(String uri, Map<FieldDefinition, String> requestData) {
        super(uri, requestData);
    }
}
