package global.coda.hms.constant.query;

/**
 * The type Doctor quries.
 */
public final class DoctorQueries {
    /**
     * The constant DOCTOR_INSERT.
     */
    public static final String DOCTOR_INSERT = "INSERT INTO `t_doctor` (`doctor_specialization`, "
            + "`fk_user_id`) VALUES (?, ?)";
    /**
     * The constant DOCTOR_SELECT_ALL.
     */
    public static final String DOCTOR_SELECT_ALL = "select user.pk_user_id,user.username,user"
            + ".password,user.firstname,user.lastname,doctor.pk_doctor_id,doctor"
            + ".doctor_specialization,"
            + "user.fk_role_id,user.city,user.state,user.created_time,user.updated_time from  "
            + "t_user_details as user LEFT JOIN t_doctor as doctor ON user.pk_user_id = doctor"
            + ".fk_user_id"
            + " where user.is_active = 1 and user.fk_role_id = 4 ";
    /**
     * The constant DOCTOR_SELECT_BY_ID.
     */
    public static final String DOCTOR_SELECT_BY_ID = "select user.pk_user_id,user.username,user"
            + ".password,user.firstname,user.lastname,doctor.pk_doctor_id,doctor"
            + ".doctor_specialization,"
            + "user.fk_role_id,user.city,user.state,user.created_time,user.updated_time from  "
            + "t_user_details as user LEFT JOIN t_doctor as doctor ON user.pk_user_id = doctor"
            + ".fk_user_id where user.is_active = 1 and "
            + "user.fk_role_id = 4 and doctor.pk_doctor_id = ?";
    /**
     * The constant DOCTOR_DELETE_BY_ID.
     */
    public static final String DOCTOR_DELETE_BY_ID = "update t_doctor set is_active = 0 where "
            + "pk_doctor_id = ?";
    /**
     * The constant DOCTOR_UPDATE_BY_ID.
     */
    public static final String DOCTOR_UPDATE_BY_ID = "UPDATE `t_doctor` SET `doctor_specialization` "
            + "= ? WHERE (`pk_doctor_id` = ?)";
    /**
     * The constant DOCTOR_FIND_DOCTOR_ID_BY_USER_ID.
     */
    public static final String DOCTOR_FIND_DOCTOR_ID_BY_USER_ID = "select pk_doctor_id from "
            + "t_doctor where fk_user_id = ? and is_active = 1";

    public static final String DOCTOR_ASSIGN_PATIENT = "insert into t_record (`fk_patient_id`,"
            + "`fk_doctor_id`,`desease`) value (?,?,?)";
    public static final String GET_PATIENTS = "select * from t_record where fk_doctor_id = ? and "
            + "is_active = 1";
    public static final String GET_UNIQUE_DOCTORS_RECORD = "select distinct(fk_doctor_id) from "
            + "t_record";
    public static final String GET_PATIENTS_OF_DOCTOR = "select patient.username,patient.firstname,"
            + "record.fk_patient_id as patient_id,patient.user_id, patient.lastname,patient"
            + ".blood_group,patient.weight,patient.city,patient.state,patient.phone_number from "
            + "t_record as record  LEFT join (select user.pk_user_id as user_id, user.username,user"
            + ".password,user.firstname,user.lastname,patient.blood_group,patient.weight,user"
            + ".fk_role_id,user.city,user.state,user.created_time,user.updated_time, patient"
            + ".pk_patient_id as patient_id,user.phone_number from  t_user_details as user LEFT JOIN "
            + "t_patient as patient ON user.pk_user_id = patient.fk_user_id where  patient.is_active "
            + "= 1 and fk_role_id = 3) as patient on patient.patient_id = fk_patient_id where record"
            + ".fk_doctor_id = ? and record.is_active = 1";
    public static final String GET_USER_ID_BY_PATIENT_ID = "select fk_user_id from t_doctor where "
            + "pk_doctor_id = ?";

    /**
     * Constructor .
     */
    private DoctorQueries() {
    }
}
