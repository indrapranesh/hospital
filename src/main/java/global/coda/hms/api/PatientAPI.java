package global.coda.hms.api;

import global.coda.hms.constant.HttpStatusConstant;
import global.coda.hms.deligate.PatientDelegate;
import global.coda.hms.exception.BusinessException;
import global.coda.hms.exception.SystemException;
import global.coda.hms.model.Patient;
import global.coda.hms.model.CustomResponse;
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

/**
 * To do a crud operations on patient entity .
 */
@Path("/api/patients")
public class PatientAPI {
    public static final Logger LOGGER = LogManager.getLogger(PatientAPI.class);
    /**
     * The Patient delegate.
     */
    private PatientDelegate patientDelegate = new PatientDelegate();

    /**
     * To create a patient in a database .
     *
     * @param newPatient patient to be created .
     * @return the created patient
     * @throws SystemException the system exception
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    public CustomResponse<?> createPatient(Patient newPatient) throws SystemException {
        LOGGER.traceEntry(newPatient.toString());
        Patient patient = patientDelegate.createPatient(newPatient);
        LOGGER.traceExit(patient.toString());
        return new CustomResponse<>().setData(patient).setStatusCode(HttpStatusConstant.OK);


    }

    /**
     * Read patient response entity.
     *
     * @param patientId the patient id
     * @return the response entity
     * @throws BusinessException the business exception
     * @throws SystemException   the system exception
     */
    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomResponse<?> readPatient(@PathParam("patientId") int patientId) throws BusinessException, SystemException {
        LOGGER.traceEntry(Integer.toString(patientId));
        Patient patient = patientDelegate.readPatient(patientId);
        LOGGER.traceExit(patient.toString());
        return new CustomResponse<>().setData(patient).setStatusCode(HttpStatusConstant.OK);
    }

    /**
     * Update patient response entity.
     *
     * @param newPatient the new patient
     * @return the response entity
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public CustomResponse<?> updatePatient(Patient newPatient) throws SystemException,
            BusinessException {
        LOGGER.traceEntry();
        Patient updatedPatient = patientDelegate.updatePatient(newPatient);
        LOGGER.traceExit();
        return new CustomResponse<>().setStatusCode(HttpStatusConstant.OK).setData(updatedPatient);

    }

    /**
     * Delete patient response.
     *
     * @param patientId the patient id
     * @return the response
     * @throws SystemException   the system exception
     * @throws BusinessException the business exception
     */
    @DELETE
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePatient(@PathParam("patientId") int patientId) throws SystemException,
            BusinessException {
        LOGGER.traceEntry();
        Boolean isPatientDeleted = patientDelegate.deletePatient(patientId);
        return Response.status(HttpStatusConstant.OK_NO_CONTENT).build();
    }


}
