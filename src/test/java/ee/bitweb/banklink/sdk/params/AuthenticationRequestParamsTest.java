package ee.bitweb.banklink.sdk.params;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tobre on 21/03/2017.
 */
public class AuthenticationRequestParamsTest {

    @Test
    public void canConstruct() throws Exception {
        new AuthenticationRequestParams();
        new AuthenticationRequestParams("EST", "UTF-8");
        new AuthenticationRequestParams("EST", "UTF-8", "http://localhost/success", "http://localhost/cancel");
    }


    @Test
    public void setLanguage() throws Exception {
        AuthenticationRequestParams params = new AuthenticationRequestParams();
        params.setLanguage("EST");
        assertEquals("EST", params.getLanguage());
    }

    @Test
    public void setEncoding() throws Exception {
        AuthenticationRequestParams params = new AuthenticationRequestParams();
        params.setEncoding("UTF-8");
        assertEquals("UTF-8", params.getEncoding());
    }


    @Test
    public void setSuccessUri() throws Exception {
        AuthenticationRequestParams params = new AuthenticationRequestParams();
        params.setSuccessUri("http://localhost/success");
        assertEquals("http://localhost/success", params.getSuccessUri());
    }


    @Test
    public void setCancelUri() throws Exception {
        AuthenticationRequestParams params = new AuthenticationRequestParams();
        params.setCancelUri("http://localhost/cancel");
        assertEquals("http://localhost/cancel", params.getCancelUri());
    }

    @Test
    public void setIfNotDefinedLanguage() throws Exception {
        AuthenticationRequestParams params = new AuthenticationRequestParams();
        params.setLanguage(null);
        params.setIfNotDefinedLanguage("EST");
        assertEquals("EST", params.getLanguage());
        params.setLanguage("FIN");
        params.setIfNotDefinedLanguage("EST");
        assertEquals("FIN", params.getLanguage());
    }

    @Test
    public void setIfNotDefinedEncoding() throws Exception {
        AuthenticationRequestParams params = new AuthenticationRequestParams();
        params.setEncoding(null);
        params.setIfNotDefinedEncoding("UTF-8");
        assertEquals("UTF-8", params.getEncoding());
        params.setEncoding("ISO");
        params.setIfNotDefinedEncoding("UTF-8");
        assertEquals("ISO", params.getEncoding());
    }

}