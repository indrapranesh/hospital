package global.coda.hms.model;

import java.util.Date;

/**
 * The type Hospital.
 */
public final class Hospital {
    private int hospitalId;
    private String hospitalName;
    private int isActive;
    private Date createdDate;
    private Date updatedDate;


    @Override
    public String toString() {
        return "Hospital{"
                + "hospitalId=" + hospitalId
                + ", hospitalName='" + hospitalName + '\''
                + ", isActive=" + isActive
                + ", createdDate=" + createdDate
                + ", updatedDate=" + updatedDate
                + '}';
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
     * Gets hospitalId.
     *
     * @return Value of hospitalId.
     */
    public int getHospitalId() {
        return hospitalId;
    }

    /**
     * Sets new hospitalId.
     *
     * @param hospitalId New value of hospitalId.
     */
    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    /**
     * Gets hospitalName.
     *
     * @return Value of hospitalName.
     */
    public String getHospitalName() {
        return hospitalName;
    }

    /**
     * Sets new hospitalName.
     *
     * @param hospitalName New value of hospitalName.
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}
