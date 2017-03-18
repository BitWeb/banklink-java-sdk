package ee.bitweb.banklinksdk.seb;

import ee.bitweb.banklinksdk.protocol.FieldDefinition;
import ee.bitweb.banklinksdk.protocol.iPizza.Fields;
import ee.bitweb.banklinksdk.protocol.iPizza.Fields2;

/**
 * Created by tobre on 18/03/2017.
 */
public class SebFields extends Fields2 {

    public SebFields() {
        super.MSG = new FieldDefinition("VK_MSG", 10);
    }
}
