package global.coda.hms.config;


import global.coda.hms.constant.DbConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class do all the database confiquration related confiqurations .
 *
 */
public final class DbConfig {

    private static final Logger LOGGER = LogManager.getLogger(DbConfig.class);
    private static Connection hospitalDbConnection;

    /**
     * doConfig method used to do the dao configuration .
     *
     * @throws ClassNotFoundException if class is not available.
     * @throws SQLException           if there is a error in sql .
     * @throws IllegalAccessException permission error .
     * @throws InstantiationException object creation errors .
     */
    public static void doConfig() throws ClassNotFoundException, SQLException,
            IllegalAccessException, InstantiationException {
        Class.forName(DbConstants.DRIVER_CLASS).newInstance();
        hospitalDbConnection = DriverManager.getConnection(DbConstants.URL, DbConstants.USERNAME,
                DbConstants.PASSWORD);
    }

    /**
     * Creating the connection .
     *
     * @return Connection object
     * @throws ClassNotFoundException the class not found exception
     * @throws SQLException           the sql exception
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     */
    public static Connection getHmsDaoConnection() throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException {
        if(hospitalDbConnection == null) {
            try {
                doConfig();
            } catch(SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                throw e;
            }
        }
        return hospitalDbConnection;
    }

    /**
     * Constructor .
     */
    private DbConfig() {
    }
}
