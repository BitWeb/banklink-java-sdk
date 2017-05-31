package ee.bitweb.banklink.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by erich on 31/05/17.
 */
public class LoggingHelper {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String parseObject(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (Exception e) {
            return "UNPRINTABLE OBJECT";
        }
    }
}
