package ee.bitweb.banklinksdk.protocol.iPizza.response;

import org.joda.time.DateTime;

/**
 * Created by tobre on 20/03/2017.
 */
public class AuthenticationResponse extends Response {

    protected String name;
    protected String socialSecurityNumber;
    protected DateTime timestamp;
    protected String message;
    protected String countryCode;

    public AuthenticationResponse(String name, String socialSecurityNumber, DateTime timestamp, String message, String senderId, String countryCode) {
        this.name = name;
        this.socialSecurityNumber = socialSecurityNumber;
        this.timestamp = timestamp;
        this.message = message;
        this.senderId = senderId;
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
