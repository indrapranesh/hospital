package global.coda.hms.bean;

import global.coda.hms.model.Patient;

import java.util.List;

/**
 * The type Doctor patient mapper.
 */
public class PatientMapper {
    /**
     * The Doctor id.
     */
    private int doctorId;
    /**
     * The Patients.
     */
    private List<Patient> patients;

    /**
     * Instantiates a new Doctor patient mapper.
     */
    public PatientMapper() {
    }

    /**
     * string .
     *
     * @return String
     */
    @Override
    public String toString() {
        return "DoctorPatientMapper{"
                + "doctorId=" + doctorId
                + ", patients=" + patients
                + '}';
    }

    /**
     * Instantiates a new Doctor patient mapper.
     *
     * @param doctorId the doctor id
     * @param patients the patients
     */
    public PatientMapper(int doctorId, List<Patient> patients) {
        this.doctorId = doctorId;
        this.patients = patients;
    }

    /**
     * Gets doctor id.
     *
     * @return the doctor id
     */
    public int getDoctorId() {
        return doctorId;
    }

    /**
     * Sets doctor id.
     *
     * @param doctorId the doctor id
     */
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * Gets patients.
     *
     * @return the patients
     */
    public List<Patient> getPatients() {
        return patients;
    }

    /**
     * Sets patients.
     *
     * @param patients the patients
     */
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
