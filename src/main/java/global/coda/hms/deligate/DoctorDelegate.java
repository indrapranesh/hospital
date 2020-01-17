package global.coda.hms.deligate;

import global.coda.hms.exception.BusinessException;
import global.coda.hms.exception.SystemException;
import global.coda.hms.helper.DoctorHelper;
import global.coda.hms.helper.PatientMappingHelper;
import global.coda.hms.model.Doctor;
import global.coda.hms.bean.PatientMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Doctor delegate.
 */
public class DoctorDelegate {
    /**
     * The constant LOGGER.
     */
    public static final Logger LOGGER = LogManager.getLogger(DoctorDelegate.class);
    /**
     * The Doctor crud helper.
     */
    private DoctorHelper doctorHelper = new DoctorHelper();
    private PatientMappingHelper patientMappingHelper = new PatientMappingHelper();

    /**
     * Create doctor response entity.
     *
     * @param doctor the doctor
     * @return the response entity
     * @throws SystemException the system exception
     */
    public Doctor createDoctor(Doctor doctor) throws SystemException {
        LOGGER.traceEntry(doctor.toString());
        Doctor newDoctor = doctorHelper.createDoctor(doctor);
        LOGGER.traceExit();
        return newDoctor;

    }

    /**
     * Read doctor response entity.
     *
     * @param doctorId the doctor id
     * @return the response entity
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public Doctor readDoctor(int doctorId) throws SystemException, BusinessException {
        LOGGER.traceEntry();
        Doctor doctor = doctorHelper.readDoctor(doctorId);
        LOGGER.traceExit();
        return doctor;
    }

    /**
     * Update doctor response entity.
     *
     * @param doctor the doctor
     * @return the response entity
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    public Doctor updateDoctor(Doctor doctor) throws BusinessException, SystemException {
        LOGGER.traceEntry(doctor.toString());
        Doctor updatedDoctor = doctorHelper.updateDoctor(doctor);
        LOGGER.traceExit(updatedDoctor.toString());
        return updatedDoctor;
    }

    /**
     * Delete doctor response.
     *
     * @param doctorId the doctor id
     * @return the response
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    public boolean deleteDoctor(int doctorId) throws BusinessException, SystemException {
        LOGGER.traceEntry();
        Boolean isDoctorDeleted = doctorHelper.deleteDoctor(doctorId);
        LOGGER.traceExit();
        return isDoctorDeleted;
    }


    /**
     * Read all patients doctor patient mapper.
     *
     * @param doctorId the doctor id
     * @return the doctor patient mapper
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    public PatientMapper readAllPatients(int doctorId) throws BusinessException,
            SystemException {
        LOGGER.traceEntry(Integer.toString(doctorId));
        PatientMapper patientMapper = patientMappingHelper.getAllPatients(doctorId);
        LOGGER.traceExit(patientMapper);
        return patientMapper;
    }

    /**
     * Gets all patients of all doctors.
     *
     * @return the all patients of all doctors
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public List<PatientMapper> getAllPatientsOfAllDoctors() throws SystemException,
            BusinessException {
        LOGGER.traceEntry();
        List<PatientMapper> patientMapperList =
                patientMappingHelper.getPatientsOfAllDoctors();
        LOGGER.traceExit(patientMapperList);
        return patientMapperList;
    }
}
