package global.coda.hms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * The type User details.
 */
public class UserDetails {
    private int userId;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private int roleId;
    private String userFirstName;
    private String userLastName;
    private String userCity;
    private String userState;
    private int isActive;
    private Date createdDate;
    private Date updatedDate;
    private String phoneNumber;


    /**
     * Instantiates a new User details.
     */
    public UserDetails() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "UserDetails{"
                + "userId=" + userId
                + ", username='" + username + '\''
                + ", password='" + password + '\''
                + ", roleId=" + roleId
                + ", userFirstName='" + userFirstName + '\''
                + ", userLastName='" + userLastName + '\''
                + ", userCity='" + userCity + '\''
                + ", userState='" + userState + '\''
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
     * Gets phonenumber.
     *
     * @return the phonenumber
     */
    public String getPhonenumber() {
        return phoneNumber;
    }

    /**
     * Sets phonenumber.
     *
     * @param phonenumber the phonenumber
     */
    public void setPhonenumber(String phonenumber) {
        this.phoneNumber = phonenumber;
    }

    /**
     * Gets username.
     *
     * @return Value of username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets new username.
     *
     * @param username New value of username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets userState.
     *
     * @return Value of userState.
     */
    public String getUserState() {
        return userState;
    }

    /**
     * Sets new userState.
     *
     * @param userState New value of userState.
     */
    public void setUserState(String userState) {
        this.userState = userState;
    }

    /**
     * Gets userCity.
     *
     * @return Value of userCity.
     */
    public String getUserCity() {
        return userCity;
    }

    /**
     * Sets new userCity.
     *
     * @param userCity New value of userCity.
     */
    public void setUserCity(String userCity) {
        this.userCity = userCity;
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
     * Gets userFirstName.
     *
     * @return Value of userFirstName.
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     * Sets new userFirstName.
     *
     * @param userFirstName New value of userFirstName.
     */
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    /**
     * Gets userLastName.
     *
     * @return Value of userLastName.
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     * Sets new userLastName.
     *
     * @param userLastName New value of userLastName.
     */
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    /**
     * Gets password.
     *
     * @return Value of password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets new password.
     *
     * @param password New value of password.
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Gets roleId.
     *
     * @return Value of roleId.
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * Sets new roleId.
     *
     * @param roleId New value of roleId.
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
