package ee.bitweb.banklinksdk.exception;

/**
 * Created by tobre on 18/03/2017.
 */
public class BanklinkException extends RuntimeException {

    public BanklinkException() {
    }

    public BanklinkException(String message) {
        super(message);
    }

    public BanklinkException(String message, Throwable cause) {
        super(message, cause);
    }

    public BanklinkException(Throwable cause) {
        super(cause);
    }

    public BanklinkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
