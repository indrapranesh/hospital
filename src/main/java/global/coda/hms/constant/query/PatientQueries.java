package global.coda.hms.constant.query;

/**
 * The type Patient quries.
 */
public final class PatientQueries {
    /**
     * The constant PATIENT_INSERT.
     */
    public static final String PATIENT_INSERT = "INSERT INTO `t_patient` (`fk_user_id`, "
            + "`blood_group`, `weight`) VALUES (?, ?, ?)";
    /**
     * The constant PATIENT_SELECT_ALL.
     */
    public static final String PATIENT_SELECT_ALL = "select user.pk_user_id,user.username,user"
            + ".password,user.firstname,user.lastname,patient.blood_group,patient.weight,user"
            + ".fk_role_id,user.city,user.state,user.created_time,user.updated_time from  "
            + "t_user_details as user LEFT JOIN t_patient as patient ON user.pk_user_id = patient"
            + ".fk_user_id where user.is_active = 1 and user.fk_role_id = 3";
    /**
     * The constant PATIENT_SELECT_BY_ID.
     */
    public static final String PATIENT_SELECT_BY_ID = "select user.pk_user_id,user.username,user"
            + ".password,user.firstname,user.lastname,patient.blood_group,patient.weight,user"
            + ".fk_role_id,user.city,user.state,user.created_time,user.updated_time,patient"
            + ".pk_patient_id from  t_user_details as user LEFT JOIN t_patient as patient ON user"
            + ".pk_user_id = patient.fk_user_id where patient.pk_patient_id = ? and patient"
            + ".is_active = "
            + "1 and fk_role_id = 3";
    /**
     * The constant GET_PATIENT_ID.
     */
    public static final String GET_PATIENT_ID = "select pk_patient_id from t_patient where "
            + "fk_user_id = ?";
    /**
     * The constant PATIENT_DELETE_BY_ID.
     */
    public static final String PATIENT_DELETE_BY_ID = "update t_patient set is_active = 0 where "
            + "pk_patient_id = ?";
    /**
     * The constant PATIENT_UPDATE_BY_ID.
     */
    public static final String PATIENT_UPDATE_BY_ID = "UPDATE `t_patient` SET `blood_group` = ?, "
            + "`weight` = ? WHERE (`pk_patient_id` = ?)";
    /**
     * Constructor .
     */
    public static final String FIND_USER_ID_BY_PATIENT_ID = "select fk_user_id from t_patient where"
            + " pk_patient_id = ?";

    /**
     * Cinstructor .
     */
    private PatientQueries() {
    }
}
