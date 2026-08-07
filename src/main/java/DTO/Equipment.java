/*
 * User Responsible: Hasbiya Yusuf
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This class represents a piece of equipment in the Maker Space system.
 * It stores equipment information including identification, category,
 * status, usage hours, hourly rate, and maintenance threshold.
 */

package DTO;

/**
 * Represents a piece of equipment within the Maker Space application.
 *
 * This Data Transfer Object (DTO) is used to transfer equipment
 * information between the application layers and corresponds to the
 * EQUIPMENT table in the database.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public class Equipment {

    private int equipmentId;
    private String assetTag;
    private String make;
    private String model;
    private String category;
    private String status;
    private double hourlyRate;
    private double usageHours;
    private double maintenanceThreshold;

    /**
     * Creates an empty Equipment object.
     */
    public Equipment() {
    }

    /**
     * Creates an Equipment object with all equipment information.
     *
     * @param equipmentId the unique equipment ID
     * @param assetTag the equipment asset tag
     * @param make the manufacturer of the equipment
     * @param model the equipment model
     * @param category the equipment category
     * @param status the current equipment status
     * @param hourlyRate the hourly usage rate
     * @param usageHours the total hours the equipment has been used
     * @param maintenanceThreshold the usage hours before maintenance is required
     */
    public Equipment(int equipmentId, String assetTag, String make, String model,
                     String category, String status, double hourlyRate, double usageHours, double maintenanceThreshold) {
        this.equipmentId = equipmentId;
        this.assetTag = assetTag;
        this.make = make;
        this.model = model;
        this.category = category;
        this.status = status;
        this.hourlyRate = hourlyRate;
        this.usageHours = usageHours;
        this.maintenanceThreshold = maintenanceThreshold;
    }

    /**
     * Returns the equipment ID.
     *
     * @return the equipment ID
     */
    public int getEquipmentId() {
        return equipmentId;
    }

    /**
     * Sets the equipment ID.
     *
     * @param equipmentId the equipment ID to assign
     */
    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    /**
     * Returns the asset tag.
     *
     * @return the asset tag
     */
    public String getAssetTag() {
        return assetTag;
    }

    /**
     * Sets the asset tag.
     *
     * @param assetTag the asset tag to assign
     */
    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    /**
     * Returns the equipment manufacturer.
     *
     * @return the manufacturer
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets the equipment manufacturer.
     *
     * @param make the manufacturer to assign
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Returns the equipment model.
     *
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the equipment model.
     *
     * @param model the model to assign
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Returns the equipment category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the equipment category.
     *
     * @param category the category to assign
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the equipment status.
     *
     * @return the current status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the equipment status.
     *
     * @param status the status to assign
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the hourly usage rate.
     *
     * @return the hourly rate
     */
    public double getHourlyRate() {
        return hourlyRate;
    }

    /**
     * Sets the hourly usage rate.
     *
     * @param hourlyRate the hourly rate to assign
     */
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    /**
     * Returns the total usage hours.
     *
     * @return the usage hours
     */
    public double getUsageHours() {
        return usageHours;
    }

    /**
     * Sets the total usage hours.
     *
     * @param usageHours the usage hours to assign
     */
    public void setUsageHours(double usageHours) {
        this.usageHours = usageHours;
    }

    /**
     * Returns the maintenance threshold.
     *
     * @return the maintenance threshold
     */
    public double getMaintenanceThreshold() {
        return maintenanceThreshold;
    }

    /**
     * Sets the maintenance threshold.
     *
     * @param maintenanceThreshold the maintenance threshold to assign
     */
    public void setMaintenanceThreshold(double maintenanceThreshold) {
        this.maintenanceThreshold = maintenanceThreshold;
    }
}