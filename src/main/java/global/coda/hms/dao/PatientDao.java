package global.coda.hms.dao;


import global.coda.hms.config.DbConfig;
import global.coda.hms.constant.NumericConstants;
import global.coda.hms.constant.query.PatientQueries;
import global.coda.hms.constant.query.UserDetailsQueries;
import global.coda.hms.model.Patient;
import global.coda.hms.helper.DaoHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Patient dao.
 */
public class PatientDao {


    private static final Logger LOGGER = LogManager.getLogger(PatientDao.class);

    /**
     * Create patient patient.
     *
     * @param patient the patient
     * @return the patient
     * @throws SQLException           the sql exception
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     * @throws ClassNotFoundException the class not found exception
     */
    public Patient createPatient(Patient patient) throws SQLException, InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        LOGGER.traceEntry();
        Connection hospitalDbConnection = DbConfig.getHmsDaoConnection();
        PreparedStatement patientPreparedStatement = null;
        PreparedStatement preparedStatementUser = null;
        try {
            hospitalDbConnection.setAutoCommit(false);
            preparedStatementUser =
                    hospitalDbConnection.prepareStatement(UserDetailsQueries.USER_DETAIL_INSERT,
                            Statement.RETURN_GENERATED_KEYS);
            DaoHelper.preparedStatment(preparedStatementUser, patient);
            int affectedRowsUser = preparedStatementUser.executeUpdate();
            if(affectedRowsUser == 1) {
                int generatedKeyUser = DaoHelper.getPrimaryKey(preparedStatementUser);
                patient.setUserId(generatedKeyUser);
                patientPreparedStatement =
                        hospitalDbConnection.prepareStatement(PatientQueries.PATIENT_INSERT,
                                Statement.RETURN_GENERATED_KEYS);
                setPatientParams(patientPreparedStatement, patient);
                int rowsAffectedpatient = patientPreparedStatement.executeUpdate();
                if(rowsAffectedpatient == 1) {
                    int generatedKeyPatient = DaoHelper.getPrimaryKey(patientPreparedStatement);
                    hospitalDbConnection.commit();
                    patient.setPatientId(generatedKeyPatient);
                } else {
                    hospitalDbConnection.rollback();
                }

            } else {
                hospitalDbConnection.rollback();
            }
        } catch(SQLException exception) {
            hospitalDbConnection.rollback();
            throw new SQLException(exception);

        } finally {
            if(patientPreparedStatement != null) {
                patientPreparedStatement.close();
            }
            if(preparedStatementUser != null) {
                preparedStatementUser.close();
            }
        }
        LOGGER.traceExit();

        //returning created patient
        return patient;
    }

    /**
     * @param patientPreparedStatement preparedStatement
     * @param patient                  patient
     * @throws SQLException sqlException
     */
    private void setPatientParams(PreparedStatement patientPreparedStatement, Patient patient) throws SQLException {
        LOGGER.traceEntry();
        patientPreparedStatement.setInt(NumericConstants.ONE, patient.getUserId());
        patientPreparedStatement.setString(NumericConstants.TWO, patient.getBloodGroup());
        patientPreparedStatement.setInt(NumericConstants.THREE, patient.getWeight());
        LOGGER.traceExit();
    }


    /**
     * Read all patient list.
     *
     * @return the list
     * @throws SQLException           the sql exception
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     * @throws ClassNotFoundException the class not found exception
     */
    public List<Patient> readAllPatient() throws SQLException,
            InstantiationException, IllegalAccessException, ClassNotFoundException {
        LOGGER.traceEntry();
        Connection hospitalDbConnection = DbConfig.getHmsDaoConnection();
        PreparedStatement preparedStatementReadAllPatient = null;
        List<Patient> patientRecords = null;
        try {
            hospitalDbConnection.setAutoCommit(false);
            preparedStatementReadAllPatient =
                    hospitalDbConnection.prepareStatement(PatientQueries.PATIENT_SELECT_ALL);
            ResultSet patientsResult = preparedStatementReadAllPatient.executeQuery();
            patientRecords = readPatientDetails(patientsResult);
            hospitalDbConnection.commit();
        } catch(SQLException exception) {
            hospitalDbConnection.rollback();
        } finally {
            if(preparedStatementReadAllPatient != null) {
                preparedStatementReadAllPatient.close();
            }
        }

        LOGGER.traceExit();
        return patientRecords;

    }

    /**
     * reading patient details from the resultset .
     *
     * @param patientResultSet patientresultset
     * @return list of patients
     * @throws SQLException sqlexception
     */
    private List<Patient> readPatientDetails(ResultSet patientResultSet) throws SQLException {
        LOGGER.traceEntry();
        List<Patient> patients = new ArrayList<>();
        while(patientResultSet.next()) {
            Patient tempPatient = new Patient();
            tempPatient.setUserId(patientResultSet.getInt(NumericConstants.ONE));
            tempPatient.setUsername(patientResultSet.getString(NumericConstants.TWO));
            tempPatient.setPassword(patientResultSet.getString(NumericConstants.THREE));
            tempPatient.setUserFirstName(patientResultSet.getString(NumericConstants.FOUR));
            tempPatient.setUserLastName(patientResultSet.getString(NumericConstants.FIVE));
            tempPatient.setBloodGroup(patientResultSet.getString(NumericConstants.SIX));
            tempPatient.setWeight(patientResultSet.getInt(NumericConstants.SEVEN));
            tempPatient.setRoleId(patientResultSet.getInt(NumericConstants.EIGHT));
            tempPatient.setUserCity(patientResultSet.getString(NumericConstants.NINE));
            tempPatient.setUserState(patientResultSet.getString(NumericConstants.TEN));
            tempPatient.setCreatedDate(patientResultSet.getDate(NumericConstants.ELEVEN));
            tempPatient.setUpdatedDate(patientResultSet.getDate(NumericConstants.TWELEVE));
            tempPatient.setPatientId(patientResultSet.getInt(NumericConstants.THIRTEEN));
            patients.add(tempPatient);

        }


        LOGGER.traceExit(patients.toString());
        return patients;

    }

    /**
     * Update patient boolean.
     *
     * @param newPatient the new patient
     * @return the boolean
     * @throws SQLException           the sql exception
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     * @throws ClassNotFoundException the class not found exception
     */
    public boolean updatePatient(Patient newPatient) throws SQLException,
            InstantiationException, IllegalAccessException, ClassNotFoundException {

        Connection hospitalDbConnection = DbConfig.getHmsDaoConnection();
        PreparedStatement updateUserDetailsPreparedStatement;
        PreparedStatement updatePatientPreparedStatement;


        hospitalDbConnection.setAutoCommit(false);
        updateUserDetailsPreparedStatement =
                hospitalDbConnection.prepareStatement(UserDetailsQueries.UPDATE_BY_ID);
        try {
            DaoHelper.setUserDetails(updateUserDetailsPreparedStatement,
                    newPatient);
            int rowsAffectedByUserDetails = updateUserDetailsPreparedStatement.executeUpdate();
            if(rowsAffectedByUserDetails == 1) {
                updatePatientPreparedStatement =
                        hospitalDbConnection.prepareStatement(PatientQueries.PATIENT_UPDATE_BY_ID);
                preparePatientStatement(updatePatientPreparedStatement, newPatient);
                int rowsAffectedOnPatient = updatePatientPreparedStatement.executeUpdate();
                return DaoHelper.checkRowsAffected(hospitalDbConnection, rowsAffectedOnPatient);
            } else {
                hospitalDbConnection.rollback();
                return false;
            }
        } catch(SQLException exception) {
            hospitalDbConnection.rollback();
            throw new SQLException(exception);
        }


    }

    /**
     * Preparing statment for update the patient .
     *
     * @param preparedStatement prepared statment
     * @param patient           patient
     * @throws SQLException sqlexception
     */
    private void preparePatientStatement(PreparedStatement preparedStatement,
                                         Patient patient) throws SQLException {
        preparedStatement.setString(NumericConstants.ONE, patient.getBloodGroup());
        preparedStatement.setInt(NumericConstants.TWO, patient.getWeight());
        preparedStatement.setInt(NumericConstants.THREE, patient.getPatientId());
    }

    /**
     * Delete patient boolean.
     *
     * @param patientId the patient id
     * @return the boolean
     * @throws SQLException           the sql exception
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     * @throws ClassNotFoundException the class not found exception
     */
    public boolean deletePatient(int patientId) throws SQLException,
            InstantiationException, IllegalAccessException, ClassNotFoundException {
        LOGGER.traceEntry(Integer.toString(patientId));

        Connection hospitalDbConnection = DbConfig.getHmsDaoConnection();
        PreparedStatement deletePatientPreparedStatement;
        try {
            deletePatientPreparedStatement =
                    hospitalDbConnection.prepareStatement(PatientQueries.PATIENT_DELETE_BY_ID);
            deletePatientPreparedStatement.setInt(NumericConstants.ONE, patientId);
            hospitalDbConnection.setAutoCommit(false);
            int numberOfRecordsDeleted = deletePatientPreparedStatement.executeUpdate();
            if(numberOfRecordsDeleted == 1) {
                hospitalDbConnection.commit();
                LOGGER.traceExit(true);
                return true;
            } else {
                hospitalDbConnection.rollback();
                LOGGER.traceExit(false);
                return false;
            }
        } catch(SQLException exception) {
            LOGGER.error(exception.getMessage());
            throw new SQLException(exception);
        }

    }

    /**
     * Read patient patient.
     *
     * @param patientId the patient id
     * @return the patient
     * @throws SQLException           the sql exception
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     * @throws ClassNotFoundException the class not found exception
     */
    public Patient readPatient(int patientId) throws SQLException,
            InstantiationException, IllegalAccessException, ClassNotFoundException {
        LOGGER.traceEntry();
        Connection hospitalDbConnection = DbConfig.getHmsDaoConnection();
        PreparedStatement getPatientByIdPreparedStatement;
        ResultSet patientResultSet;
        try {
            getPatientByIdPreparedStatement =
                    hospitalDbConnection.prepareStatement(PatientQueries.PATIENT_SELECT_BY_ID);
            getPatientByIdPreparedStatement.setInt(NumericConstants.ONE, patientId);
            patientResultSet = getPatientByIdPreparedStatement.executeQuery();
        } catch(SQLException exception) {
            throw new SQLException(exception);
        }
        LOGGER.traceExit();
        List<Patient> patients = readPatientDetails(patientResultSet);
        if(patients.size() > 0) {
            return patients.get(NumericConstants.ZERO);
        } else {
            return null;
        }
    }


    /**
     * Read user id by patient id int.
     *
     * @param patientId the patient id
     * @return the int
     * @throws ClassNotFoundException the class not found exception
     * @throws SQLException           the sql exception
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     */
    public int readUserIdByPatientId(int patientId) throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException {
        LOGGER.traceEntry(Integer.toString(patientId));
        Connection connection = DbConfig.getHmsDaoConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement(PatientQueries.FIND_USER_ID_BY_PATIENT_ID);
        preparedStatement.setInt(NumericConstants.ONE, patientId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int userId = resultSet.getInt(NumericConstants.ONE);
        return userId;
    }
}

