package global.coda.hms.constant.query;

/**
 * Quries for access the userDetails entity .
 */
public final class UserDetailsQueries {

    public static final String USER_DETAIL_INSERT = "INSERT INTO `t_user_details` (`username`, "
            + "`password`, `fk_role_id`, `firstname`, `lastname`, `city`, `state`,`phone_number`) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?,?);";
    public static final String USER_DELETE_BY_ID = "update t_user_details set is_active = 0 where "
            + "pk_user_id = ?";
    public static final String UPDATE_BY_ID = "UPDATE `t_user_details` SET `username` = ?, "
            + "`password` = ?, `firstname` = ?, `lastname` = ?, `city` = ?, `state` = ? WHERE "
            + "(`pk_user_id` = ?)";
    public static final String SELECT_BY_ID = "select * from t_user_details where username = ? and "
            + "password = ?";

    /**
     * Constructor .
     */
    private UserDetailsQueries() {
    }
}
