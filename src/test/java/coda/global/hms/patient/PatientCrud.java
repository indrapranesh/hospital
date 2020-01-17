package coda.global.hms.patient;

import global.coda.hms.constant.NumericConstants;
import global.coda.hms.dao.PatientDao;
import global.coda.hms.model.Patient;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;

/**
 * The type Patient crud.
 */
@Test
public class PatientCrud {
    /**
     * Create patient.
     *
     * @throws ClassNotFoundException the class not found exception
     * @throws SQLException           the sql exception
     * @throws InstantiationException the instantiation exception
     * @throws IllegalAccessException the illegal access exception
     */
    @Test
    public void createPatient() throws ClassNotFoundException, SQLException, InstantiationException,
            IllegalAccessException {
        PatientDao patientDao = new PatientDao();
        Patient patient = new Patient();
        patient.setUsername("pranesh");
        patient.setPassword("test");
        patient.setRoleId(NumericConstants.THREE);
        Patient createdPatient = null;

        createdPatient = patientDao.createPatient(patient);
        Assert.assertEquals(createdPatient.getUsername(), patient.getUsername());


    }
}
