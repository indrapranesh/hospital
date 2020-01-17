package global.coda.hms.exception.hospital;

/**
 * When searched hospital is not found .
 */
public class HospitalNotFoundException extends Exception {
    /**
     * Constructor .
     *
     * @param errorMessage error
     */
    public HospitalNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
