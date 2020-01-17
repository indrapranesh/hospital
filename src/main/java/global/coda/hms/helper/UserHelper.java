package global.coda.hms.helper;

import global.coda.hms.dao.UserDao;
import global.coda.hms.exception.SystemException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

/**
 * The type User helper.
 */
public class UserHelper {

    private static final Logger LOGGER = LogManager.getLogger(UserHelper.class);
    private UserDao userDao = new UserDao();

    /**
     * Delete user boolean.
     *
     * @param userId the user id
     * @return the boolean
     * @throws SystemException the system exception
     */
    public boolean deleteUser(int userId) throws SystemException {
        LOGGER.traceEntry(Integer.toString(userId));
        try {
            boolean isUserDeleted = userDao.deleteUser(userId);
            LOGGER.traceExit(isUserDeleted);
            return isUserDeleted;
        } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SystemException(e);
        } catch(Exception e) {
            LOGGER.error(e.getMessage());
            throw new SystemException(e);
        }
    }

}
