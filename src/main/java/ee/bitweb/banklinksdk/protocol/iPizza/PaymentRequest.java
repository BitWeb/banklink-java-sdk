package ee.bitweb.banklinksdk.protocol.iPizza;

import ee.bitweb.banklinksdk.protocol.FieldDefinition;

import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public class PaymentRequest {


    protected String bankUri;
    protected Map<FieldDefinition, String> requestData;

    public PaymentRequest(String uri, Map<FieldDefinition, String> requestData) {
        this.bankUri = uri;
        this.requestData = requestData;
    }

    public String createRequestHtml() {
        String data = "<html><body><form id=\"bankForm\" method=\"POST\" action=\""+this.bankUri+"\">\n";
        data += renderHiddenValues();
        data += "</form>\n";
        data += "<script type=\"text/javascript\">document.getElementById(\"bankForm\").submit();</script></body></html>\n";
        return data;
    }

    private String renderHiddenValues() {
        String data = "";
        for (FieldDefinition field : requestData.keySet()) {
            data += "<input type=\"hidden\" name=\""+ field.getName() +"\" value=\"" + requestData.get(field) + "\"/>\n";
        }

        return data;
    }
}
