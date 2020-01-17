package global.coda.hms.constant.query;

/**
 * The type Hospital quries.
 */
public final class HospitalQueries {
    /**
     * The constant CREATE_HOSPITAL.
     */
    public static final String CREATE_HOSPITAL = "INSERT INTO `hms`.`t_hospital` (`hospital_name`) "
            + "VALUES (?)";
    /**
     * The constant READ_HOSPITAL_BY_ID.
     */
    public static final String READ_HOSPITAL_BY_ID = "select * from t_hospital where pk_hospital_id"
            + " = ? and is_active = 1";
    /**
     * The constant READ_HOSPITAL_ALL.
     */
    public static final String READ_HOSPITAL_ALL = "select * from t_hospital where is_active = 1";
    /**
     * The constant UPDATE_HOSPTIAL_BY_ID.
     */
    public static final String UPDATE_HOSPTIAL_BY_ID = "UPDATE `t_hospital` SET `hospital_name` = ?"
            + " WHERE (`pk_hospital_id` = ?)";
    /**
     * The constant DELETE_HOSPITAL_BY_ID.
     */
    public static final String DELETE_HOSPITAL_BY_ID = "update t_hospital set is_active = 0 where "
            + "pk_hospital_id = ?";

    /**
     * Constructor .
     */
    private HospitalQueries() {
    }
}
