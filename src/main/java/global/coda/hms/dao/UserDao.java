package global.coda.hms.dao;

import global.coda.hms.config.DbConfig;
import global.coda.hms.constant.NumericConstants;
import global.coda.hms.constant.query.UserDetailsQueries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The type User dao.
 */
public class UserDao {
    /**
     * The constant LOGGER.
     */
    public static final Logger LOGGER = LogManager.getLogger(UserDao.class);

    /**
     * Delete patient boolean.
     *
     * @param userId the user id
     * @return the boolean
     * @throws ClassNotFoundException the class not found exception
     * @throws SQLException           the sql exception
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     */
    public boolean deleteUser(int userId) throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException {
        LOGGER.traceEntry(Integer.toString(userId));
        Connection dbConnection = DbConfig.getHmsDaoConnection();
        dbConnection.setAutoCommit(false);
        PreparedStatement preparedStatement =
                dbConnection.prepareStatement(UserDetailsQueries.USER_DELETE_BY_ID);
        preparedStatement.setInt(NumericConstants.ONE, userId);
        int rowsAffected = preparedStatement.executeUpdate();
        if(rowsAffected == 1) {
            LOGGER.traceExit(true);
            dbConnection.commit();
            return true;
        } else {
            dbConnection.rollback();
            LOGGER.traceExit(false);
            return false;

        }
    }
}
