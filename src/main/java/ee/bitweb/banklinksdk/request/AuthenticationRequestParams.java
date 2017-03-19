package ee.bitweb.banklinksdk.request;

public class AuthenticationRequestParams {

    protected String language;
    protected String encoding;

    protected String successUri;
    protected String cancelUri;

    public AuthenticationRequestParams() {}

    AuthenticationRequestParams(String language, String encoding) {
        this();
        this.language = language;
        this.encoding = encoding;

    }

    AuthenticationRequestParams(String language, String encoding, String successUri, String cancelUri) {
        this(language, encoding);
        this.successUri = successUri;
        this.cancelUri = cancelUri;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getSuccessUri() {
        return successUri;
    }

    public void setSuccessUri(String successUri) {
        this.successUri = successUri;
    }

    public String getCancelUri() {
        return cancelUri;
    }

    public void setCancelUri(String cancelUri) {
        this.cancelUri = cancelUri;
    }

    public void setIfNotDefinedLanguage(String language) {
        this.language = this.language == null ? language : this.language;
    }

    public void setIfNotDefinedEncoding(String encoding) {
        this.encoding = this.encoding == null ? encoding : this.encoding;
    }


}
