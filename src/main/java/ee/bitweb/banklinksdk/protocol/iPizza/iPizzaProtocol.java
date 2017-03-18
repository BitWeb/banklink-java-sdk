package ee.bitweb.banklinksdk.protocol.iPizza;

import ee.bitweb.banklinksdk.protocol.FieldDefinition;
import ee.bitweb.banklinksdk.protocol.Protocol;
import ee.bitweb.banklinksdk.protocol.Vendor;
import ee.bitweb.banklinksdk.request.PaymentRequestParams;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public class iPizzaProtocol extends Protocol {

    protected String publicKey;
    protected String privateKey;

    protected Vendor vendor;


    public iPizzaProtocol(String publicKey, String privateKey, Vendor vendor) {
        //TODO: Check parameters and throw IllegalArgumentException

        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.vendor = vendor;
    }

    public Map<FieldDefinition, String> preparePaymentRequest(PaymentRequestParams paymentRequestParams) {
        Map<FieldDefinition, String> requestData = new HashMap<>();
        /*requestData.put(Fields.SERVICE, Services.PAYMENT_REQUEST);
        requestData.put(Fields.VERSION, version);

        requestData.put(Fields.SND_ID, vendor.getSenderId());
        requestData.put(Fields.STAMP, Services.PAYMENT_REQUEST); //FIXME siia mingi jama
        requestData.put(Fields.AMOUNT, paymentRequestParams.getAmount());
        requestData.put(Fields.CURR, paymentRequestParams.getCurrency());
        requestData.put(Fields.REF, paymentRequestParams.getReferenceNumber());*/
        requestData.put(Fields.MSG, paymentRequestParams.getMessage());
        /*requestData.put(Fields.RETURN_URL, paymentRequestParams.getSuccessUri());
        requestData.put(Fields.CANCEL_URL, paymentRequestParams.getCancelUri());
        requestData.put(Fields.DATETIME, new Date());

        requestData.put(Fields.ENCODING, paymentRequestParams.getEncoding());
        requestData.put(Fields.LANG, paymentRequestParams.getLanguage());*/


        //requestData.put(Field2.MAC, getRequestSignature(requestData));

        return requestData;

    }


    protected String getRequestSignature(Map<Fields, Object> requestData) {
        String mac = getMac(requestData);

        //TODO: signing with private key

        return "";
    }

    protected String getMac(Services service, Map<Fields, Object> requestData) {
        String data = "";
        for ( Fields field : Services.getFields(service)) {

            data += padMacParameter(field.length, (String) requestData.get(field));
        }

        return data;
    }

    private String padMacParameter(int length, String value) {
        String prefix = "";
        int prefixLen = length - value.length();

        for (int i = 0; i < prefixLen; i++) {
            prefix += "0";
        }

        return prefix + value;
    }
}
