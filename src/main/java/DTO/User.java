/*
 * User Responsible: Hasbiya Yusuf
 * Date: 05/08/2026
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This class represents a user in the Maker Space system. It stores
 * user account information including personal details, login credentials,
 * account type, available credit balance, and the account creation date.
 */

package DTO;

import java.sql.Timestamp;

/**
 * Represents a user within the Maker Space application.
 *
 * This Data Transfer Object (DTO) is used to transfer user information
 * between the application layers and corresponds to the USER table in the
 * database.
 *
 * @author HY
 * @version 1.0
 */
public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private String userType;
    private double creditBalance;
    private Timestamp createdAt;

    /**
     * Creates an empty User object.
     */
    public User() {
    }

    /**
     * Creates a User object with all user information.
     *
     * @param userId the unique user ID
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param email the user's email address
     * @param passwordHash the hashed password
     * @param userType the user's role in the system
     * @param creditBalance the user's available credit balance
     * @param createdAt the account creation timestamp
     */
    public User(int userId, String firstName, String lastName, String email,
                String passwordHash, String userType, double creditBalance,
                Timestamp createdAt) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.userType = userType;
        this.creditBalance = creditBalance;
        this.createdAt = createdAt;
    }

    /**
     * Returns the user's unique ID.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user's unique ID.
     *
     * @param userId the user ID to assign
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Returns the user's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name.
     *
     * @param firstName the first name to assign
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the user's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name.
     *
     * @param lastName the last name to assign
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the user's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email the email address to assign
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the user's hashed password.
     *
     * @return the password hash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets the user's hashed password.
     *
     * @param passwordHash the password hash to assign
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Returns the user's role.
     *
     * @return the user type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the user's role.
     *
     * @param userType the user type to assign
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Returns the user's available credit balance.
     *
     * @return the credit balance
     */
    public double getCreditBalance() {
        return creditBalance;
    }

    /**
     * Sets the user's available credit balance.
     *
     * @param creditBalance the credit balance to assign
     */
    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    /**
     * Returns the account creation timestamp.
     *
     * @return the creation timestamp
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the account creation timestamp.
     *
     * @param createdAt the timestamp to assign
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}