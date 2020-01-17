package global.coda.hms.model;

import java.sql.Date;

/**
 * The type Patient.
 */
public final class Patient extends UserDetails {
    private int patientId;
    private int userId;
    private String bloodGroup;
    private int weight;
    private int isActive;
    private Date createdDate;
    private Date updatedDate;


    /**
     * Instantiates a new Patient.
     */
    public Patient() {
    }

    @Override
    public String toString() {
        return "Patient{"
                + "patientId=" + patientId
                + ", userId=" + userId
                + ", bloodGroup='" + bloodGroup + '\''
                + ", weight=" + weight
                + ", isActive=" + isActive
                + ", createdDate=" + createdDate
                + ", updatedDate=" + updatedDate
                + '}';
    }

    /**
     * Gets bloodGroup.
     *
     * @return Value of bloodGroup.
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * Sets new bloodGroup.
     *
     * @param bloodGroup New value of bloodGroup.
     */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    /**
     * Gets createdDate.
     *
     * @return Value of createdDate.
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets new createdDate.
     *
     * @param createdDate New value of createdDate.
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets patientId.
     *
     * @return Value of patientId.
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * Sets new patientId.
     *
     * @param patientId New value of patientId.
     */
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    /**
     * Gets userId.
     *
     * @return Value of userId.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets new userId.
     *
     * @param userId New value of userId.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets weight.
     *
     * @return Value of weight.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets new weight.
     *
     * @param weight New value of weight.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Gets isActive.
     *
     * @return Value of isActive.
     */
    public int getIsActive() {
        return isActive;
    }

    /**
     * Sets new isActive.
     *
     * @param isActive New value of isActive.
     */
    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    /**
     * Gets updatedDate.
     *
     * @return Value of updatedDate.
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets new updatedDate.
     *
     * @param updatedDate New value of updatedDate.
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
