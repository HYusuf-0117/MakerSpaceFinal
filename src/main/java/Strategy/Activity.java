/*
 * User Responsible: Owen Cabrera
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This class represents an activity that can earn credits for a user.
 */

package strategy;

import java.sql.Timestamp;

/**
 * Represents an activity that earns credits.
 *
 * @author Owen Cabrera
 * @version 1.0
 */
public class Activity {
    private int activityId;
    private int userId;
    private String activityType; // "DONATION", "TRAINING", "MAINTENANCE", "WORK_ORDER"
    private double hours;
    private double donationValue;
    private int workOrdersCompleted;
    private int trainingHours;
    private int maintenanceHours;
    private Timestamp activityDate;
    private String description;

    // Constructor
    public Activity() {}

    public Activity(String activityType, double hours, double donationValue, 
                    int workOrdersCompleted, int trainingHours, int maintenanceHours) {
        this.activityType = activityType;
        this.hours = hours;
        this.donationValue = donationValue;
        this.workOrdersCompleted = workOrdersCompleted;
        this.trainingHours = trainingHours;
        this.maintenanceHours = maintenanceHours;
    }

    // Getters and Setters
    public int getActivityId() { return activityId; }
    public void setActivityId(int activityId) { this.activityId = activityId; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public String getActivityType() { return activityType; }
    public void setActivityType(String activityType) { this.activityType = activityType; }
    
    public double getHours() { return hours; }
    public void setHours(double hours) { this.hours = hours; }
    
    public double getDonationValue() { return donationValue; }
    public void setDonationValue(double donationValue) { this.donationValue = donationValue; }
    
    public int getWorkOrdersCompleted() { return workOrdersCompleted; }
    public void setWorkOrdersCompleted(int workOrdersCompleted) { this.workOrdersCompleted = workOrdersCompleted; }
    
    public int getTrainingHours() { return trainingHours; }
    public void setTrainingHours(int trainingHours) { this.trainingHours = trainingHours; }
    
    public int getMaintenanceHours() { return maintenanceHours; }
    public void setMaintenanceHours(int maintenanceHours) { this.maintenanceHours = maintenanceHours; }
    
    public Timestamp getActivityDate() { return activityDate; }
    public void setActivityDate(Timestamp activityDate) { this.activityDate = activityDate; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}