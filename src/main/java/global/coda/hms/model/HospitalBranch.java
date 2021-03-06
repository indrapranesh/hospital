package global.coda.hms.model;

import java.util.Date;

/**
 * The type Hospital branch.
 */
public final class HospitalBranch {
    private int branchId;
    private int hospitalId;
    private int isActive;
    private String branchAddress;
    private Date createdDate;
    private Date updatedDate;


    /**
     * Gets branchId.
     *
     * @return Value of branchId.
     */
    public int getBranchId() {
        return branchId;
    }

    /**
     * Sets new branchId.
     *
     * @param branchId New value of branchId.
     */
    public void setBranchId(int branchId) {
        this.branchId = branchId;
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

    /**
     * Gets branchAddress.
     *
     * @return Value of branchAddress.
     */
    public String getBranchAddress() {
        return branchAddress;
    }

    /**
     * Sets new branchAddress.
     *
     * @param branchAddress New value of branchAddress.
     */
    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
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
}
