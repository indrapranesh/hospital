package global.coda.hms.model;

import java.util.Date;

/**
 * Doctor model .
 *
 * @author Indra Pranesh
 */
public final class Doctor extends UserDetails {
    private int doctor_id;
    private int userId;
    private String doctor_specialization;
    private int isActive;
    private Date createdTime;
    private Date updatedTime;


    @Override
    public String toString() {
        return "Doctor{"
                + "doctor_id=" + doctor_id
                + ", userId=" + userId
                + ", specilization='" + doctor_specialization + '\''
                + ", isActive=" + isActive
                + ", createdTime=" + createdTime
                + ", updatedTime=" + updatedTime
                + '}';
    }

    /**
     * Gets createdTime.
     *
     * @return Value of createdTime.
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * Sets new createdTime.
     *
     * @param createdTime New value of createdTime.
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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
     * Gets updatedTime.
     *
     * @return Value of updatedTime.
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * Sets new updatedTime.
     *
     * @param updatedTime New value of updatedTime.
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * Gets doctor_id.
     *
     * @return Value of doctor_id.
     */
    public int getDoctor_id() {
        return doctor_id;
    }

    /**
     * Sets new doctor_id.
     *
     * @param doctor_id New value of doctor_id.
     */
    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    /**
     * Gets specilization.
     *
     * @return Value of specilization.
     */
    public String getDoctor_specialization() {
        return doctor_specialization;
    }

    /**
     * Sets new specilization.
     *
     * @param doctor_specialization New value of specilization.
     */
    public void setDoctor_specialization(String doctor_specialization) {
        this.doctor_specialization = doctor_specialization;
    }
}
