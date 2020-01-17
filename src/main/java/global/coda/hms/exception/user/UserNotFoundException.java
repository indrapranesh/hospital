package global.coda.hms.exception.user;

/**
 * Arrives when no user is found .
 */
public class UserNotFoundException extends Exception {
    /**
     * Constructor .
     *
     * @param errorMessage error
     */
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
