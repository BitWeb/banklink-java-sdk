package ee.bitweb.banklinksdk.seb;

import ee.bitweb.banklinksdk.protocol.FieldDefinition;
import ee.bitweb.banklinksdk.protocol.iPizza.Fields;

/**
 * Created by tobre on 18/03/2017.
 */
public class SebFields extends Fields {

    public SebFields() {
        super.MSG = new FieldDefinition("VK_MSG", 10);
    }
}
