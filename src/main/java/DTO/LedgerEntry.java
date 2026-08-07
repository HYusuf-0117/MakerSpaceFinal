/*
 * User Responsible: Hasbiya Yusuf
 * Date: 05/08/2026
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This class represents a ledger entry in the Maker Space system. It stores
 * information about credit and debit transactions associated with a user,
 * including the transaction source, amount, description, and date.
 */

package DTO;

import java.sql.Timestamp;

/**
 * Represents a ledger entry within the Maker Space application.
 *
 * This Data Transfer Object (DTO) is used to transfer ledger transaction
 * information between the application layers and corresponds to the
 * LEDGER_ENTRY table in the database.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public class LedgerEntry {

    private int ledgerId;
    private int userId;
    private String entryType;
    private double amount;
    private String sourceType;
    private Integer sourceId;
    private String description;
    private Timestamp entryDate;

    /**
     * Creates an empty LedgerEntry object.
     */
    public LedgerEntry() {
    }

    /**
     * Creates a LedgerEntry object with all transaction information.
     *
     * @param ledgerId the unique ledger entry ID
     * @param userId the ID of the associated user
     * @param entryType the transaction type (Credit or Debit)
     * @param amount the transaction amount
     * @param sourceType the source of the transaction
     * @param sourceId the ID of the related source record
     * @param description a description of the transaction
     * @param entryDate the date and time the transaction occurred
     */
    public LedgerEntry(int ledgerId, int userId, String entryType, double amount,
                       String sourceType, Integer sourceId, String description,
                       Timestamp entryDate) {
        this.ledgerId = ledgerId;
        this.userId = userId;
        this.entryType = entryType;
        this.amount = amount;
        this.sourceType = sourceType;
        this.sourceId = sourceId;
        this.description = description;
        this.entryDate = entryDate;
    }

    /**
     * Returns the ledger entry ID.
     *
     * @return the ledger entry ID
     */
    public int getLedgerId() {
        return ledgerId;
    }

    /**
     * Sets the ledger entry ID.
     *
     * @param ledgerId the ledger entry ID to assign
     */
    public void setLedgerId(int ledgerId) {
        this.ledgerId = ledgerId;
    }

    /**
     * Returns the associated user ID.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the associated user ID.
     *
     * @param userId the user ID to assign
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Returns the transaction type.
     *
     * @return the transaction type
     */
    public String getEntryType() {
        return entryType;
    }

    /**
     * Sets the transaction type.
     *
     * @param entryType the transaction type to assign
     */
    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    /**
     * Returns the transaction amount.
     *
     * @return the transaction amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the transaction amount.
     *
     * @param amount the transaction amount to assign
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Returns the transaction source type.
     *
     * @return the source type
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * Sets the transaction source type.
     *
     * @param sourceType the source type to assign
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * Returns the related source ID.
     *
     * @return the source ID
     */
    public Integer getSourceId() {
        return sourceId;
    }

    /**
     * Sets the related source ID.
     *
     * @param sourceId the source ID to assign
     */
    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * Returns the transaction description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the transaction description.
     *
     * @param description the description to assign
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the transaction date and time.
     *
     * @return the transaction timestamp
     */
    public Timestamp getEntryDate() {
        return entryDate;
    }

    /**
     * Sets the transaction date and time.
     *
     * @param entryDate the timestamp to assign
     */
    public void setEntryDate(Timestamp entryDate) {
        this.entryDate = entryDate;
    }
}