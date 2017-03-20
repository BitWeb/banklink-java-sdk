package ee.bitweb.banklink.sdk.protocol.iPizza;

import ee.bitweb.banklink.sdk.protocol.FieldDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tobre on 18/03/2017.
 */
public enum Services {

    //Requests
    PAYMENT_REQUEST("1012"),
    AUTHENTICATE_REQUEST("4012"),

    //Responses
    PAYMENT_SUCCESS("1111"),
    PAYMENT_ERROR("1911"),
    AUTHENTICATE_SUCCESS("3013");

    public String code;

    Services(String code) {
        this.code = code;
    }

    public static List<FieldDefinition> getFields(Services service) {
        List<FieldDefinition> fields = new ArrayList<>();

        switch (service) {
            case PAYMENT_REQUEST:
                fields.add(Fields.SERVICE);
                fields.add(Fields.VERSION);

                fields.add(Fields.SND_ID);
                fields.add(Fields.STAMP);
                fields.add(Fields.AMOUNT);
                fields.add(Fields.CURR);
                fields.add(Fields.REF);
                fields.add(Fields.MSG);
                fields.add(Fields.RETURN_URL);
                fields.add(Fields.CANCEL_URL);
                fields.add(Fields.DATETIME);

                return fields;
            case AUTHENTICATE_REQUEST:
                fields.add(Fields.SERVICE);
                fields.add(Fields.VERSION);

                fields.add(Fields.SND_ID);
                fields.add(Fields.REC_ID);
                fields.add(Fields.NONCE);
                fields.add(Fields.RETURN_URL);
                fields.add(Fields.DATETIME);
                fields.add(Fields.RID);

                return fields;
            case PAYMENT_SUCCESS:
                fields.add(Fields.SERVICE);
                fields.add(Fields.VERSION);

                fields.add(Fields.SND_ID);
                fields.add(Fields.REC_ID);
                fields.add(Fields.STAMP);
                fields.add(Fields.T_NO);
                fields.add(Fields.AMOUNT);
                fields.add(Fields.CURR);
                fields.add(Fields.REC_ACC);
                fields.add(Fields.REC_NAME);
                fields.add(Fields.SND_ACC);
                fields.add(Fields.SND_NAME);
                fields.add(Fields.REF);
                fields.add(Fields.MSG);
                fields.add(Fields.T_DATETIME);
                fields.add(Fields.LANG);
                fields.add(Fields.AUTO);

                return fields;
            case PAYMENT_ERROR:
                fields.add(Fields.SERVICE);
                fields.add(Fields.VERSION);

                fields.add(Fields.SND_ID);
                fields.add(Fields.REC_ID);
                fields.add(Fields.STAMP);
                fields.add(Fields.REF);
                fields.add(Fields.MSG);
                fields.add(Fields.LANG);
                fields.add(Fields.AUTO);

                return fields;
            case AUTHENTICATE_SUCCESS:
                fields.add(Fields.SERVICE);
                fields.add(Fields.VERSION);

                fields.add(Fields.DATETIME);
                fields.add(Fields.SND_ID);
                fields.add(Fields.REC_ID);
                fields.add(Fields.USER_NAME);
                fields.add(Fields.USER_ID);
                fields.add(Fields.COUNTRY);
                fields.add(Fields.OTHER);
                fields.add(Fields.TOKEN);
                fields.add(Fields.RID);

                return fields;
        }

        return fields;
    }

    public static Services getByCode(String code) {
        for(Services e : values()) {
            if(e.code.equals(code)) return e;
        }
        return null;
    }
}
