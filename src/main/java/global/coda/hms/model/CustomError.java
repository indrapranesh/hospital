package global.coda.hms.model;

/**
 * To Generate a custome error .
 */
public class CustomError {

    /**
     * The Error message.
     */
    private String errorMessage;

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets error message.
     *
     * @param errorMessage the error message
     * @return the error message
     */
    public CustomError setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

}
