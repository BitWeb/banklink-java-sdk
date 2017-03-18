package ee.bitweb.banklinksdk.protocol.iPizza;

import ee.bitweb.banklinksdk.protocol.Protocol;
import ee.bitweb.banklinksdk.protocol.Vendor;

import java.util.HashMap;
import java.util.List;
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

    public void prepareRequest(Map<String, Object> requestData) {
        /**
         *
         $this->addMacParameter(Constants::SERVICE, $service);
         $this->addMacParameter(Constants::VERSION, $this->version);
         $this->addParameter(Constants::MAC);
         $this->addParameter(Constants::ENCODING, $this->encoding);
         $this->addParameter(Constants::LANG, $this->language);
         */

       //requestData.put();


    }
}
