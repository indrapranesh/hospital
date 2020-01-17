package global.coda.hms.helper;

import global.coda.hms.dao.DoctorDao;
import global.coda.hms.exception.BusinessException;
import global.coda.hms.exception.SystemException;
import global.coda.hms.exception.doctor.DoctorNotFoundException;
import global.coda.hms.model.Doctor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

/**
 * The type Doctor crud helper.
 */
public class DoctorHelper {
    public static final Logger LOGGER = LogManager.getLogger(DoctorHelper.class);
    private UserHelper userHelper = new UserHelper();
    /**
     * The Doctor dao.
     */
    private DoctorDao doctorDao = new DoctorDao();

    /**
     * Create doctor response entity.
     *
     * @param doctor the doctor
     * @return the response entity
     * @throws SystemException the system exception
     */
    public Doctor createDoctor(Doctor doctor) throws SystemException {
        LOGGER.traceEntry();
        try {
            Doctor newDoctor = doctorDao.createDoctor(doctor);
            LOGGER.traceExit();
            return newDoctor;
        } catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            LOGGER.traceExit();
            throw new SystemException(e);
        }
    }

    /**
     * Read doctor response entity.
     *
     * @param doctorId the doctor id
     * @return the response entity
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    public Doctor readDoctor(int doctorId) throws BusinessException, SystemException {
        try {
            Doctor doctor = doctorDao.getDoctor(doctorId);
            LOGGER.traceEntry();
            return doctor;

        } catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            LOGGER.traceExit();
            throw new SystemException(e);
        } catch(DoctorNotFoundException e) {
            LOGGER.traceExit();
            throw new BusinessException(e);
        }
    }

    /**
     * Update doctor response entity.
     *
     * @param doctor the doctor
     * @return the response entity
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public Doctor updateDoctor(Doctor doctor) throws SystemException, BusinessException {
        try {
            LOGGER.traceEntry();
            doctorDao.updateDoctor(doctor);
            Doctor updatedDoctor = doctorDao.getDoctor(doctor.getDoctor_id());
            LOGGER.traceExit();
            return updatedDoctor;
        } catch(SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            LOGGER.traceExit();
            throw new SystemException(e);
        } catch(DoctorNotFoundException e) {
            LOGGER.traceExit();
            throw new BusinessException(e);
        }
    }

    /**
     * Delete doctor response.
     *
     * @param doctorId the doctor id
     * @return the response
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    public Boolean deleteDoctor(int doctorId) throws SystemException, BusinessException {
        try {
            LOGGER.traceEntry(Integer.toString(doctorId));
            boolean isDoctorDeleted = doctorDao.deleteDoctor(doctorId);
            int userId = doctorDao.getUserIdByDoctorId(doctorId);
            boolean isUserDeleted = userHelper.deleteUser(userId);
            LOGGER.traceExit();
            return isDoctorDeleted;

        } catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            LOGGER.traceExit();
            throw new SystemException(e);
        } catch(DoctorNotFoundException e) {
            LOGGER.traceExit();
            throw new BusinessException(e);
        }
    }


}
