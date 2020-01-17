package global.coda.hms.helper;

import global.coda.hms.constant.NumericConstants;
import global.coda.hms.model.UserDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Used for utility functions of dao connection .
 *
 * @author Indra Pranesh
 */
public final class DaoHelper {


    /**
     * this function fill all the parameters to the corresponding index of the preparesStatement of
     * the t_user_details.
     *
     * @param userDetailsPreparedStatement for prepare the statment
     * @param user                         user for update
     * @return result parameter filled prepared statement of the t_user
     * @throws SQLException while assigning the value for the statment
     */
    public static PreparedStatement preparedStatment(PreparedStatement userDetailsPreparedStatement,
                                                     UserDetails user) throws SQLException {

        userDetailsPreparedStatement.setString(NumericConstants.ONE, user.getUsername());
        userDetailsPreparedStatement.setString(NumericConstants.TWO, user.getPassword());
        userDetailsPreparedStatement.setInt(NumericConstants.THREE, user.getRoleId());
        userDetailsPreparedStatement.setString(NumericConstants.FOUR, user.getUserFirstName());
        userDetailsPreparedStatement.setString(NumericConstants.FIVE, user.getUserLastName());
        userDetailsPreparedStatement.setString(NumericConstants.SIX, user.getUserCity());
        userDetailsPreparedStatement.setString(NumericConstants.SEVEN, user.getUserState());
        userDetailsPreparedStatement.setString(NumericConstants.EIGHT, user.getPhonenumber());
        return userDetailsPreparedStatement;
    }

    /**
     * Preparing statment for update .
     *
     * @param userDetailsUpdatePrepareStatment preparedStatment object
     * @param user                             user need to be updated
     * @return the prepared statment object
     * @throws SQLException when issue in statement assignment
     */
    public static PreparedStatement setUserDetails(PreparedStatement userDetailsUpdatePrepareStatment, UserDetails user) throws SQLException {

        userDetailsUpdatePrepareStatment.setString(NumericConstants.ONE, user.getUsername());
        userDetailsUpdatePrepareStatment.setString(NumericConstants.TWO, user.getPassword());
        userDetailsUpdatePrepareStatment.setString(NumericConstants.THREE, user.getUserFirstName());
        userDetailsUpdatePrepareStatment.setString(NumericConstants.FOUR, user.getUserLastName());
        userDetailsUpdatePrepareStatment.setString(NumericConstants.FIVE, user.getUserCity());
        userDetailsUpdatePrepareStatment.setString(NumericConstants.SIX, user.getUserState());
        userDetailsUpdatePrepareStatment.setInt(NumericConstants.SEVEN, user.getUserId());

        return userDetailsUpdatePrepareStatment;
    }

    /**
     * Gets generated primary key.
     *
     * @param preparedStatement to get a primary key field
     * @return this function return a auto generated primary key of a prepared statement.
     * @throws SQLException while getting the primary key
     */
    public static int getPrimaryKey(PreparedStatement preparedStatement) throws SQLException {

        ResultSet keyResultSet = preparedStatement.getGeneratedKeys();
        int generatedKey = 0;
        if(keyResultSet.next()) {
            generatedKey = keyResultSet.getInt(1);
        }
        keyResultSet.close();

        return generatedKey;
    }

    /**
     * Check rows affected boolean.
     *
     * @param hospitalDbConnection the hospital db connection
     * @param rowsAffectedInDoctor the rows affected in doctor
     * @return the boolean
     * @throws SQLException the sql exception
     */
    public static boolean checkRowsAffected(Connection hospitalDbConnection,
                                            int rowsAffectedInDoctor) throws SQLException {
        if(rowsAffectedInDoctor == 1) {
            hospitalDbConnection.commit();
            return true;
        } else {
            hospitalDbConnection.rollback();
            return false;
        }
    }

    /**
     * Utility class constructor .
     */
    private DaoHelper() {
    }
}
