package global.coda.hms.constant;

/**
 * Global constants for an application .
 */
public final class ApplicationConstant {

    public static final String RESOURCE_FILE = "user/userMessages";
    public static final String USER_NOT_FOUND = "USER NOT FOUND!!!";
    public static final String PATIENT_NOT_FOUND = "PATIENT NOT FOUND!!!";
    public static final String DOCTOR_NOT_FOUND = "DOCTOR_NOT_FOUND!!!";
    public static final String EXPECTED_INTEGER = "Expected Integer Input only";
    public static final String OPTION_NOT_FOUND = "Selected Option Not available try another";
    public static final String HOSPITAL_NOT_FOUND = "HOSPITAL NOT FOUND!!!";
    public static final String LOGIN_AGAIN = "Login failed, try again!!";
    public static final String PATIENT_ASSIGN_FAILED = "Unable Assign patient to a doctor!!!";
    public static final String NO_PATIENT_FOUND_FOR_DOCTOR = "Doctor didn't have any patient";
    public static final String NO_RECORD_FOUND = "No Data Found !!";
    public static final String WENT_WRONG = "Something Went Wrong!!!";

    /**
     * Role of an users .
     */
    public enum ROLE {
        ADMIN,
        GLOBAL_ADMIN,
        PATIENT,
        DOCTOR
    }

    /**
     * Constructor .
     */
    private ApplicationConstant() {
    }
}
