package ee.bitweb.banklink.sdk.protocol.iPizza.request;

import ee.bitweb.banklink.sdk.protocol.FieldDefinition;

import java.util.Map;

/**
 * Created by tobre on 20/03/2017.
 */
public class Request {

    protected String requestUri;
    protected Map<FieldDefinition, String> requestData;

    public Request(String requestUri, Map<FieldDefinition, String> requestData) {
        this.requestUri = requestUri;
        this.requestData = requestData;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public Map<FieldDefinition, String> getRequestData() {
        return requestData;
    }

    public String createRequestHtml() {
        String data = "<html><body><form id=\"bankForm\" method=\"POST\" action=\""+this.requestUri +"\">\n";
        data += renderHiddenValues();
        data += "</form>\n";
        data += "<script type=\"text/javascript\">document.getElementById(\"bankForm\").submit();</script></body></html>\n";
        return data;
    }

    private String renderHiddenValues() {
        String data = "";
        for (FieldDefinition field : this.requestData.keySet()) {
            data += "<input type=\"hidden\" name=\""+ field.getName() +"\" value=\"" + this.requestData.get(field) + "\"/>\n";
        }

        return data;
    }
}
