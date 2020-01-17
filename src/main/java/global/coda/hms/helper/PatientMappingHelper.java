package global.coda.hms.helper;

import global.coda.hms.constant.ApplicationConstant;
import global.coda.hms.dao.DoctorDao;
import global.coda.hms.exception.BusinessException;
import global.coda.hms.exception.SystemException;
import global.coda.hms.exception.doctor.DoctorNotFoundException;
import global.coda.hms.exception.doctor.NoRecordFoundException;
import global.coda.hms.model.Doctor;
import global.coda.hms.bean.PatientMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Doctor patient mapping helper.
 */
public class PatientMappingHelper {
    private static final Logger LOGGER = LogManager.getLogger(PatientMappingHelper.class);
    private DoctorDao doctorDao = new DoctorDao();

    /**
     * Gets all patients.
     *
     * @param doctorId the doctor id
     * @return the all patients
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public PatientMapper getAllPatients(int doctorId) throws SystemException,
            BusinessException {
        LOGGER.traceEntry(Integer.toString(doctorId));
        PatientMapper patientMapper;
        try {
            patientMapper = doctorDao.getAllPatients(doctorId);
            if(patientMapper == null) {
                throw new NoRecordFoundException(ApplicationConstant.NO_RECORD_FOUND);
            } else {
                return patientMapper;
            }
        } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            LOGGER.traceExit(e.getMessage());
            throw new SystemException(e);
        } catch(NoRecordFoundException e) {
            throw new BusinessException(e);
        } catch(Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * Gets all patients of all doctors.
     *
     * @return the all patients of all doctors
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    public List<PatientMapper> getPatientsOfAllDoctors() throws BusinessException,
            SystemException {
        LOGGER.traceEntry();
        try {
            List<Doctor> doctors = doctorDao.getAllDoctor();
            List<PatientMapper> patientMapperList = new ArrayList<>();
            if(doctors != null) {
                for(Doctor doctor : doctors) {
                    PatientMapper patientMapper = getAllPatients(doctor.getDoctor_id());
                    if(patientMapper != null) {
                        patientMapperList.add(patientMapper);
                    }

                }
                if(patientMapperList.size() > 0) {
                    LOGGER.traceExit(patientMapperList);
                    return patientMapperList;
                } else {
                    throw new NoRecordFoundException(ApplicationConstant.NO_RECORD_FOUND);
                }
            } else {
                throw new DoctorNotFoundException(ApplicationConstant.DOCTOR_NOT_FOUND);
            }
        } catch(SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new SystemException(e);
        } catch(DoctorNotFoundException | NoRecordFoundException e) {
            throw new BusinessException(e);
        } catch(SystemException e) {
            throw e;

        }
    }
}


