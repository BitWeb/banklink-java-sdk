package ee.bitweb.banklinksdk.protocol.iPizza;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    AUTHENTICATE_SUCCESS("3012");

    public String code;

    Services(String code) {
        this.code = code;
    }

    public static List<Fields> getFields(Services service) {
        List<Fields> fields = new ArrayList<>();

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

                fields.add(Fields.USER);
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
}
