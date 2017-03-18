package ee.bitweb.banklinksdk.seb;

import ee.bitweb.banklinksdk.protocol.FieldDefinition;
import ee.bitweb.banklinksdk.protocol.iPizza.Fields;

/**
 * Created by tobre on 18/03/2017.
 */
public class SebFields extends Fields {

    public SebFields() {

        super.SND_ID.setLength(15);
        super.REC_ID.setLength(15);
        super.STAMP.setLength(20);
        super.T_NO.setLength(20);
        super.AMOUNT.setLength(12);
        super.REC_ACC.setLength(34);
        super.REC_NAME.setLength(70);
        super.SND_ACC.setLength(34);
        super.SND_NAME.setLength(70);
        super.NAME.setLength(70);
        super.REF.setLength(35);
        super.MSG.setLength(95);
        super.RETURN_URL.setLength(255);
        super.CANCEL_URL.setLength(255);
        super.RID.setLength(30);
        super.MAC.setLength(700);
        super.USER.setLength(16);
        super.USER_NAME.setLength(140);
        super.USER_ID.setLength(20);
        super.OTHER.setLength(150);
    }
}
