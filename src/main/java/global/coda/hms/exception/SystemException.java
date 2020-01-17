package global.coda.hms.exception;

/**
 * When unexpected exception arrives it will be handled there .
 */
public final class SystemException extends Exception {


    /**
     * Instantiates a new System exception.
     *
     * @param e the e
     */
    public SystemException(Exception e) {
        super(e);
    }


}
