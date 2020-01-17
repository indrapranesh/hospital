package global.coda.hms.dao;


import global.coda.hms.config.DbConfig;
import global.coda.hms.constant.ApplicationConstant;
import global.coda.hms.constant.NumericConstants;
import global.coda.hms.constant.query.DoctorQueries;
import global.coda.hms.constant.query.UserDetailsQueries;
import global.coda.hms.exception.doctor.DoctorNotFoundException;
import global.coda.hms.model.Doctor;
import global.coda.hms.model.Patient;
import global.coda.hms.bean.PatientMapper;
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
 * The type Doctor.
 *
 * @author Indra Pranesh
 */
public class DoctorDao {

    /**
     * The Patient dao.
     */
    private PatientDao patientDao = new PatientDao();

    private static final Logger LOGGER = LogManager.getLogger(DoctorDao.class);

    /**
     * to create the doctor.
     *
     * @param doctor to create
     * @return the doctor
     * @throws SQLException           the sql exception
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     * @throws ClassNotFoundException the class not found exception
     */
    public Doctor createDoctor(Doctor doctor) throws SQLException, InstantiationException,
            IllegalAccessException, ClassNotFoundException {


        LOGGER.traceEntry();
        Connection dbConnection = DbConfig.getHmsDaoConnection();
        PreparedStatement userDetailCreatePreparedStatment = null;
        PreparedStatement doctorCreatePreparedStatement = null;
        try {
            dbConnection.setAutoCommit(false);
            userDetailCreatePreparedStatment =
                    dbConnection.prepareStatement(UserDetailsQueries.USER_DETAIL_INSERT,
                            Statement.RETURN_GENERATED_KEYS);
            DaoHelper.preparedStatment(userDetailCreatePreparedStatment, doctor);
            int numRowsAffectedUser = userDetailCreatePreparedStatment.executeUpdate();
            if(numRowsAffectedUser == 1) {
                int generatedUserId = DaoHelper.getPrimaryKey(userDetailCreatePreparedStatment);
                doctor.setUserId(generatedUserId);
                doctorCreatePreparedStatement = dbConnection.prepareStatement(DoctorQueries.DOCTOR_INSERT,
                        Statement.RETURN_GENERATED_KEYS);
                setDoctorParameters(doctorCreatePreparedStatement, doctor);
                doctorCreatePreparedStatement.executeUpdate();
                int generatedKeyDoctor = DaoHelper.getPrimaryKey(doctorCreatePreparedStatement);
                doctor.setDoctor_id(generatedKeyDoctor);
                dbConnection.commit();
            } else {
                dbConnection.rollback();
            }

        } catch(SQLException exception) {
            throw new SQLException(exception);
        } finally {
            if(userDetailCreatePreparedStatment != null) {
                userDetailCreatePreparedStatment.close();
            }
            if(doctorCreatePreparedStatement != null) {
                doctorCreatePreparedStatement.close();
            }
        }
        LOGGER.traceExit();
        return doctor;
    }

    /**
     * used for assign the doctor parameters.
     *
     * @param preparedStatementCreateDoctor for set doctor parameters
     * @param doctor                        to be created
     * @throws SQLException when statment closed
     */
    private void setDoctorParameters(PreparedStatement preparedStatementCreateDoctor,
                                     Doctor doctor) throws SQLException {
        preparedStatementCreateDoctor.setString(NumericConstants.ONE,
                doctor.getDoctor_specialization());
        preparedStatementCreateDoctor.setInt(NumericConstants.TWO, doctor.getUserId());
    }

    /**
     * To get all doctors .
     *
     * @return the list of doctors
     * @throws SQLException            while executing the query
     * @throws DoctorNotFoundException while no doctors found
     * @throws InstantiationException  the instantiation exception
     * @throws IllegalAccessException  the illegal access exception
     * @throws ClassNotFoundException  the class not found exception
     */
    public List<Doctor> getAllDoctor() throws SQLException, DoctorNotFoundException,
            InstantiationException, IllegalAccessException, ClassNotFoundException {
        LOGGER.traceEntry();
        Connection hospitalDbConnection = DbConfig.getHmsDaoConnection();
        PreparedStatement preparedStatement;
        preparedStatement = hospitalDbConnection.prepareStatement(DoctorQueries.DOCTOR_SELECT_ALL);
        ResultSet doctorResultSet = preparedStatement.executeQuery();
        LOGGER.traceExit();
        return readDoctorFromResultSet(doctorResultSet);
    }

    /**
     * read doctor from the resultset .
     *
     * @param resultSet resultset of an doctor
     * @return list of doctos
     * @throws SQLException            when query fails
     * @throws DoctorNotFoundException when no doctors found
     */

    private List<Doctor> readDoctorFromResultSet(ResultSet resultSet) throws SQLException,
            DoctorNotFoundException {
        List<Doctor> doctors = new ArrayList<>();
        while(resultSet.next()) {
            Doctor tempDoctor = new Doctor();
            tempDoctor.setUserId(resultSet.getInt("pk_user_id"));
            tempDoctor.setUsername(resultSet.getString("username"));
            tempDoctor.setPassword(resultSet.getString("password"));
            tempDoctor.setUserFirstName(resultSet.getString("firstname"));
            tempDoctor.setUserLastName(resultSet.getString("lastname"));
            tempDoctor.setDoctor_id(resultSet.getInt("pk_doctor_id"));
            tempDoctor.setDoctor_specialization(resultSet.getString("doctor_specialization"));
            tempDoctor.setRoleId(resultSet.getInt("fk_role_id"));
            tempDoctor.setUserCity(resultSet.getString("city"));
            tempDoctor.setCreatedTime(resultSet.getTimestamp("created_time"));
            tempDoctor.setUpdatedTime(resultSet.getTimestamp("updated_time"));
            tempDoctor.setUserState(resultSet.getString("state"));
            doctors.add(tempDoctor);
        }
        if(doctors.size() > 0) {
            return doctors;
        } else {
            return null;
        }
    }

    /**
     * get doctor from the docter id.
     *
     * @param doctorId the doctor id
     * @return doctor object
     * @throws SQLException            the sql exception
     * @throws DoctorNotFoundException the doctor not found exception
     * @throws InstantiationException  the instantiation exception
     * @throws IllegalAccessException  the illegal access exception
     * @throws ClassNotFoundException  the class not found exception
     */
    public Doctor getDoctor(int doctorId) throws SQLException, DoctorNotFoundException,
            InstantiationException, IllegalAccessException, ClassNotFoundException {
        LOGGER.traceEntry();
        Connection hospitalDbConnection = DbConfig.getHmsDaoConnection();
        hospitalDbConnection.setAutoCommit(false);
        PreparedStatement readPatientPreparedStatement = null;
        ResultSet resultSetPatient = null;
        List<Doctor> doctors;
        try {
            readPatientPreparedStatement =
                    hospitalDbConnection.prepareStatement(DoctorQueries.DOCTOR_SELECT_BY_ID);
            readPatientPreparedStatement.setInt(1, doctorId);
            resultSetPatient = readPatientPreparedStatement.executeQuery();
            doctors = readDoctorFromResultSet(resultSetPatient);
            hospitalDbConnection.commit();
        } catch(SQLException exception) {
            throw new SQLException(exception);
        } finally {
            if(readPatientPreparedStatement != null) {
                readPatientPreparedStatement.close();
            }
            if(resultSetPatient != null) {
                resultSetPatient.close();
            }
        }
        LOGGER.traceExit();
        return doctors.get(0);
    }

    /**
     * Delete the doctor using the doctor id .
     *
     * @param doctorId the doctor id
     * @return the boolean value
     * @throws SQLException            when query execution fails
     * @throws DoctorNotFoundException doctor not found
     * @throws InstantiationException  the instantiation exception
     * @throws IllegalAccessException  the illegal access exception
     * @throws ClassNotFoundException  the class not found exception
     */
    public boolean deleteDoctor(int doctorId) throws SQLException, DoctorNotFoundException,
            InstantiationException, IllegalAccessException, ClassNotFoundException {
        LOGGER.traceEntry();
        Connection dbConnection = DbConfig.getHmsDaoConnection();
        dbConnection.setAutoCommit(false);

        PreparedStatement deletePatientStatement =
                dbConnection.prepareStatement(DoctorQueries.DOCTOR_DELETE_BY_ID);
        deletePatientStatement.setInt(NumericConstants.ONE, doctorId);
        int rowsAffectedPatient = deletePatientStatement.executeUpdate();
        PreparedStatement deleteUserStatement =
                dbConnection.prepareStatement(UserDetailsQueries.USER_DELETE_BY_ID);

        if(rowsAffectedPatient == 1) {
            dbConnection.commit();
            LOGGER.traceExit();
            return true;
        } else {
            dbConnection.rollback();
            LOGGER.traceExit();
            return false;
        }


    }

    /**
     * used fpor update the doctor.
     *
     * @param newDoctor newdoc
     * @return boolean boolean
     * @throws SQLException            while query fails
     * @throws DoctorNotFoundException when no doctor found
     * @throws InstantiationException  the instantiation exception
     * @throws IllegalAccessException  the illegal access exception
     * @throws ClassNotFoundException  the class not found exception
     */
    public boolean updateDoctor(Doctor newDoctor) throws SQLException, DoctorNotFoundException,
            InstantiationException, IllegalAccessException, ClassNotFoundException {
        Doctor oldDoctor = getDoctor(newDoctor.getDoctor_id());
        Connection hospitalDbConnection = DbConfig.getHmsDaoConnection();
        if(oldDoctor != null) {
            PreparedStatement userDetailUpdatePreparedStatement;
            PreparedStatement doctorPreparedStatement;
            try {
                userDetailUpdatePreparedStatement =
                        hospitalDbConnection.prepareStatement(UserDetailsQueries.UPDATE_BY_ID);
                DaoHelper.setUserDetails(userDetailUpdatePreparedStatement,
                        newDoctor);
                int rowsAffectedUserDetails = userDetailUpdatePreparedStatement.executeUpdate();
                if(rowsAffectedUserDetails == 1) {
                    doctorPreparedStatement =
                            hospitalDbConnection.prepareStatement(DoctorQueries.DOCTOR_UPDATE_BY_ID);
                    prepareStatementForDoctorUpdate(doctorPreparedStatement, newDoctor);
                    int rowsAffectedInDoctor = doctorPreparedStatement.executeUpdate();
                    return DaoHelper.checkRowsAffected(hospitalDbConnection, rowsAffectedInDoctor);
                } else {
                    hospitalDbConnection.rollback();
                    return false;
                }
            } catch(SQLException exception) {
                hospitalDbConnection.rollback();
                throw new SQLException(exception);
            }


        } else {
            throw new DoctorNotFoundException(ApplicationConstant.DOCTOR_NOT_FOUND);
        }

    }


    /**
     * Gets doctor id by user id.
     *
     * @param userId the user id
     * @return the doctor id by user id
     * @throws SQLException            the sql exception
     * @throws DoctorNotFoundException the doctor not found exception
     * @throws InstantiationException  the instantiation exception
     * @throws IllegalAccessException  the illegal access exception
     * @throws ClassNotFoundException  the class not found exception
     */
    public int getDoctorIdByUserId(int userId) throws SQLException, DoctorNotFoundException,
            InstantiationException, IllegalAccessException, ClassNotFoundException {
        Connection connection = DbConfig.getHmsDaoConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement(DoctorQueries.DOCTOR_FIND_DOCTOR_ID_BY_USER_ID);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            throw new DoctorNotFoundException(ApplicationConstant.DOCTOR_NOT_FOUND);
        }
    }

    /**
     * Gets user id by doctor id.
     *
     * @param doctorId the doctor id
     * @return the user id by doctor id
     * @throws ClassNotFoundException the class not found exception
     * @throws SQLException           the sql exception
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     */
    public int getUserIdByDoctorId(int doctorId) throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException {
        LOGGER.trace(Integer.toString(doctorId));
        Connection dbConnection = DbConfig.getHmsDaoConnection();
        PreparedStatement preparedStatement =
                dbConnection.prepareStatement(DoctorQueries.GET_USER_ID_BY_PATIENT_ID);
        preparedStatement.setInt(NumericConstants.ONE, doctorId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int userId = resultSet.getInt(NumericConstants.ONE);
        return userId;
    }

    /**
     * prepare a statment for update .
     *
     * @param preparedStatement preparedStatment
     * @param doctor            doctor
     * @throws SQLException while query fails
     */
    private void prepareStatementForDoctorUpdate(PreparedStatement preparedStatement,
                                                 Doctor doctor) throws SQLException {
        preparedStatement.setString(1, doctor.getDoctor_specialization());
        preparedStatement.setInt(2, doctor.getDoctor_id());

    }


    /**
     * Gets all patients.
     *
     * @param doctorId the doctor id
     * @return the all patients
     * @throws ClassNotFoundException the class not found exception
     * @throws SQLException           the sql exception
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     */
    public PatientMapper getAllPatients(int doctorId) throws ClassNotFoundException,
            SQLException, InstantiationException, IllegalAccessException {
        LOGGER.traceEntry(Integer.toString(doctorId));
        Connection dbConnection = DbConfig.getHmsDaoConnection();
        PreparedStatement preparedStatement =
                dbConnection.prepareStatement(DoctorQueries.GET_PATIENTS_OF_DOCTOR);
        PatientMapper patientMapper;
        preparedStatement.setInt(NumericConstants.ONE, doctorId);
        ResultSet resultSet = preparedStatement.executeQuery();
        patientMapper = makePatientRecord(doctorId, resultSet);
        if(patientMapper != null) {
            LOGGER.traceExit(patientMapper);
            return patientMapper;
        } else {
            LOGGER.traceExit(null);
            return null;
        }

    }

    /**
     * making resultAs Doctorpatient mpper .
     *
     * @param doctorId  doctorId
     * @param resultSet resultSer
     * @return Doctopatientmapper
     * @throws SQLException sqlException
     */
    private PatientMapper makePatientRecord(int doctorId, ResultSet resultSet) throws SQLException {
        LOGGER.traceEntry(Integer.toString(doctorId), resultSet);
        PatientMapper patientMapper = new PatientMapper();
        patientMapper.setDoctorId(doctorId);
        List<Patient> patients = new ArrayList<Patient>();
        while(resultSet.next()) {
            Patient patient = new Patient();
            patient.setUsername(resultSet.getString("username"));
            patient.setUserFirstName(resultSet.getString("firstname"));
            patient.setPatientId(resultSet.getInt("patient_id"));
            patient.setUserId(resultSet.getInt("user_id"));
            patient.setUserLastName(resultSet.getString("lastname"));
            patient.setBloodGroup(resultSet.getString("blood_group"));
            patient.setWeight(resultSet.getInt("weight"));
            patient.setUserCity(resultSet.getString("city"));
            patient.setUserState(resultSet.getString("state"));
            patient.setPhonenumber(resultSet.getString("phone_number"));
            patients.add(patient);
        }

        if(patients.size() > 0) {
            patientMapper.setPatients(patients);
            LOGGER.traceExit(patientMapper);
            return patientMapper;
        } else {
            LOGGER.traceExit(null);
            return null;
        }

    }
}
