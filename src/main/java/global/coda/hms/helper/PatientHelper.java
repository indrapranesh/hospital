package global.coda.hms.helper;


import global.coda.hms.constant.ApplicationConstant;
import global.coda.hms.dao.PatientDao;
import global.coda.hms.exception.BusinessException;
import global.coda.hms.exception.SystemException;
import global.coda.hms.exception.patient.PatientNotFoundException;
import global.coda.hms.model.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

/**
 * The type Patient crud helper.
 */
public class PatientHelper {
    public static final Logger LOGGER = LogManager.getLogger(PatientHelper.class);

    /**
     * The Patient dao.
     */
    private PatientDao patientDao = new PatientDao();
    private UserHelper userHelper = new UserHelper();

    /**
     * Create patient response entity.
     *
     * @param newPatient the new patient
     * @return the response entity
     * @throws SystemException the system exception
     */
    public Patient createPatient(Patient newPatient) throws SystemException {
        Patient createdPatient;
        LOGGER.traceEntry();
        try {
            createdPatient = patientDao.createPatient(newPatient);
            LOGGER.traceExit();
            return createdPatient;
        } catch(SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
            throw new SystemException(e);
        }
    }

    /**
     * Read patient response entity.
     *
     * @param patientId the patient id
     * @return the response entity
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    public Patient readPatient(int patientId) throws BusinessException, SystemException {
        try {
            LOGGER.traceEntry();
            Patient patient = patientDao.readPatient(patientId);
            if(patient == null) {

                throw new PatientNotFoundException(ApplicationConstant.PATIENT_NOT_FOUND);
            }
            LOGGER.traceExit(patient);
            return patient;

        } catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            LOGGER.traceExit(e.getMessage());
            throw new SystemException(e);
        } catch(PatientNotFoundException e) {
            LOGGER.traceExit(e.getMessage());
            throw new BusinessException(e);
        }
    }

    /**
     * Update patient response entity.
     *
     * @param newPatient the new patient
     * @return the response entity
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public Patient updatePatient(Patient newPatient) throws SystemException,
            BusinessException {
        try {
            LOGGER.traceEntry(newPatient.toString());
            Patient oldPatient = patientDao.readPatient(newPatient.getPatientId());
            if(oldPatient == null) {
                throw new PatientNotFoundException(ApplicationConstant.PATIENT_NOT_FOUND);
            }
            patientDao.updatePatient(newPatient);
            LOGGER.traceExit(newPatient);
            Patient updatedPatient = readPatient(newPatient.getPatientId());
            return updatedPatient;
        } catch(IllegalAccessException | ClassNotFoundException | InstantiationException | SQLException e) {
            LOGGER.traceExit(e.getMessage());
            throw new SystemException(e);
        } catch(PatientNotFoundException e) {
            LOGGER.traceExit(e.getMessage());
            throw new BusinessException(e);
        }
    }

    /**
     * Delete patient response.
     *
     * @param patientId the patient id
     * @return the response
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public boolean deletePatient(int patientId) throws SystemException, BusinessException {
        try {

            Patient oldPatient = patientDao.readPatient(patientId);
            if(oldPatient == null) {
                throw new PatientNotFoundException(ApplicationConstant.PATIENT_NOT_FOUND);
            }
            boolean isPatientUpdated = patientDao.deletePatient(patientId);
            int userId = patientDao.readUserIdByPatientId(patientId);
            boolean isUserDeleted = userHelper.deleteUser(userId);
            LOGGER.traceExit(true);
            return isPatientUpdated;
        } catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
            throw new SystemException(e);
        } catch(PatientNotFoundException e) {
            LOGGER.error(e.getMessage());
            throw new BusinessException(e);
        }
    }
}
