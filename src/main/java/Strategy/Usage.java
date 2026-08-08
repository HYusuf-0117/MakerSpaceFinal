/*
 * User Responsible: Owen Cabrera
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This class represents equipment usage that incurs debits.
 */

package strategy;

import java.sql.Timestamp;

/**
 * Represents equipment usage that incurs debits.
 *
 * @author Owen Cabrera 
 * @version 1.0
 */
public class Usage {
    private int usageId;
    private int userId;
    private int equipmentId;
    private double hoursUsed;
    private double materialConsumed;
    private String materialType;
    private Timestamp startTime;
    private Timestamp endTime;

    // Constructor
    public Usage() {}

    public Usage(int equipmentId, double hoursUsed, double materialConsumed, String materialType) {
        this.equipmentId = equipmentId;
        this.hoursUsed = hoursUsed;
        this.materialConsumed = materialConsumed;
        this.materialType = materialType;
    }

    // Getters and Setters
    public int getUsageId() { return usageId; }
    public void setUsageId(int usageId) { this.usageId = usageId; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public int getEquipmentId() { return equipmentId; }
    public void setEquipmentId(int equipmentId) { this.equipmentId = equipmentId; }
    
    public double getHoursUsed() { return hoursUsed; }
    public void setHoursUsed(double hoursUsed) { this.hoursUsed = hoursUsed; }
    
    public double getMaterialConsumed() { return materialConsumed; }
    public void setMaterialConsumed(double materialConsumed) { this.materialConsumed = materialConsumed; }
    
    public String getMaterialType() { return materialType; }
    public void setMaterialType(String materialType) { this.materialType = materialType; }
    
    public Timestamp getStartTime() { return startTime; }
    public void setStartTime(Timestamp startTime) { this.startTime = startTime; }
    
    public Timestamp getEndTime() { return endTime; }
    public void setEndTime(Timestamp endTime) { this.endTime = endTime; }
}