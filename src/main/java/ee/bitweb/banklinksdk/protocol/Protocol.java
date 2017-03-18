package ee.bitweb.banklinksdk.protocol;

import java.util.Map;

/**
 * Created by tobre on 18/03/2017.
 */
public abstract class Protocol {

    protected Boolean isTestMode = false;
    protected String version;

    public Boolean isTestMode() {
        return isTestMode;
    }

    public void setTestMode(Boolean testMode) {
        isTestMode = testMode;
    }

    abstract public void prepareRequest(Map<String, Object> requestData);
}
