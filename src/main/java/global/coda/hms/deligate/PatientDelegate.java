package global.coda.hms.deligate;

import global.coda.hms.exception.BusinessException;
import global.coda.hms.exception.SystemException;
import global.coda.hms.helper.PatientHelper;
import global.coda.hms.model.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Business class for patient operations .
 */
public class PatientDelegate {
    public static final Logger LOGGER = LogManager.getLogger(PatientDelegate.class);
    /**
     * The Patient crud helper.
     */
    private PatientHelper patientHelper = new PatientHelper();

    /**
     * used to create the patient .
     *
     * @param newPatient new Patient object
     * @return the created object
     * @throws SystemException when error at sql or db
     */
    public Patient createPatient(Patient newPatient) throws SystemException {
        LOGGER.traceEntry(newPatient.toString());
        Patient patient = patientHelper.createPatient(newPatient);
        LOGGER.traceExit(newPatient.toString());
        return patient;
    }

    /**
     * Used to read the patient.
     *
     * @param patientId the patient id
     * @return response obj of the patient
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public Patient readPatient(int patientId) throws SystemException, BusinessException {
        LOGGER.traceEntry(Integer.toString(patientId));
        Patient updatedPatient = patientHelper.readPatient(patientId);
        LOGGER.traceExit();
        return updatedPatient;

    }

    /**
     * Update patient response entity.
     *
     * @param newPatient the new patient
     * @return the response entity
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    public Patient updatePatient(Patient newPatient) throws BusinessException,
            SystemException {
        LOGGER.traceEntry();
        Patient updatedPatient = patientHelper.updatePatient(newPatient);
        LOGGER.traceExit();
        return updatedPatient;
    }

    /**
     * Delete patient response.
     *
     * @param patientId the patient id
     * @return the response
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    public Boolean deletePatient(int patientId) throws BusinessException, SystemException {
        LOGGER.traceEntry(Integer.toString(patientId));
        Boolean isPatientDeleted = patientHelper.deletePatient(patientId);
        LOGGER.traceExit(isPatientDeleted);
        return isPatientDeleted;

    }
}
