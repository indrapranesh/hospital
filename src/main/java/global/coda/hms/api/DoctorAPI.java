package global.coda.hms.api;

import global.coda.hms.constant.HttpStatusConstant;
import global.coda.hms.deligate.DoctorDelegate;
import global.coda.hms.exception.BusinessException;
import global.coda.hms.exception.SystemException;
import global.coda.hms.model.Doctor;
import global.coda.hms.model.CustomResponse;
import global.coda.hms.bean.PatientMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type Doctor service.
 */
@Path("/api/doctors")
public class DoctorAPI {

    /**
     * The constant LOGGER.
     */
    public static final Logger LOGGER = LogManager.getLogger(DoctorAPI.class);

    /**
     * The Doctor delegate.
     */
    private DoctorDelegate doctorDelegate = new DoctorDelegate();

    /**
     * Create doctor response entity.
     *
     * @param doctor the doctor
     * @return the response entity
     * @throws SystemException the system exception
     */
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomResponse<?> createDoctor(Doctor doctor) throws SystemException {
        LOGGER.traceEntry(doctor.toString());
        Doctor newDoctor = doctorDelegate.createDoctor(doctor);
        LOGGER.traceExit(newDoctor);
        return new CustomResponse<>().setStatusCode(HttpStatusConstant.OK).setData(newDoctor);
    }

    /**
     * Read doctor response entity.
     *
     * @param doctorId the doctor id
     * @return the response entity
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    @GET
    @Path("/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomResponse<?> readDoctor(@PathParam("doctorId") int doctorId) throws BusinessException, SystemException {
        LOGGER.traceEntry(Integer.toString(doctorId));
        Doctor doctor = doctorDelegate.readDoctor(doctorId);
        LOGGER.traceExit(doctor);
        return new CustomResponse<>().setData(doctor).setStatusCode(HttpStatusConstant.OK);
    }

    /**
     * Update doctor response entity.
     *
     * @param doctor the doctor
     * @return the response entity
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CustomResponse<?> updateDoctor(Doctor doctor) throws SystemException, BusinessException {
        LOGGER.traceEntry(doctor.toString());
        Doctor updatedDoctor = doctorDelegate.updateDoctor(doctor);
        LOGGER.traceExit(updatedDoctor.toString());
        return new CustomResponse<>().setData(updatedDoctor).setStatusCode(HttpStatusConstant.OK);
    }

    /**
     * Delete doctor response.
     *
     * @param doctorId the doctor id
     * @return the response
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    @DELETE
    @Path("/{doctorId}")
    public Response deleteDoctor(@PathParam("doctorId") int doctorId) throws SystemException,
            BusinessException {
        LOGGER.traceEntry(Integer.toString(doctorId));
        boolean isDoctorDeleted = doctorDelegate.deleteDoctor(doctorId);
        LOGGER.traceExit(isDoctorDeleted);
        return Response.status(HttpStatusConstant.OK_NO_CONTENT).build();
    }

    /**
     * Gets all patients of doctor.
     *
     * @param doctorId the doctor id
     * @return the all patients of doctor
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    @GET
    @Path("/{doctorId}/patients")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomResponse<?> getAllPatientsOfDoctor(@PathParam("doctorId") int doctorId) throws SystemException,
            BusinessException {
        LOGGER.traceEntry(Integer.toString(doctorId));
        PatientMapper patientMapper = doctorDelegate.readAllPatients(doctorId);
        LOGGER.traceExit(patientMapper);
        return new CustomResponse<>().setData(patientMapper).setStatusCode(HttpStatusConstant.OK);
    }

    /**
     * Gets all patients of all doctors.
     *
     * @return the all patients of all doctors
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    @GET
    @Path("/doctors/patients/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomResponse<?> getAllPatientsOfAllDoctors() throws BusinessException, SystemException {
        LOGGER.traceEntry();
        List<PatientMapper> patientMapperList = doctorDelegate.getAllPatientsOfAllDoctors();
        LOGGER.traceExit(patientMapperList);
        return new CustomResponse<>().setData(patientMapperList).setStatusCode(HttpStatusConstant.OK);
    }
}
