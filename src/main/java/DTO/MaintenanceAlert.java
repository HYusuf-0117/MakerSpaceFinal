package DTO;
import java.sql.Timestamp;
/**
 * This Data Transfer Object (DTO) is used to transfer Maintenance alert
 * information between the application layers and corresponds to the
 * Maintenance_Alert table in the database.
 * @author Auston Gurr
 */
public class MaintenanceAlert {
    private int alertID;
    private int equipmentId;
    private String alertMessage;
    private double wearHours;
    private Timestamp timestamp;
    
    public MaintenanceAlert(){}
    
    public MaintenanceAlert(int equipmentId, String alertMessage, double wearHours, Timestamp timestamp) {
        this.equipmentId = equipmentId;
        this.alertMessage = alertMessage;
        this.wearHours = wearHours;
        this.timestamp = timestamp;
    }

    public int getAlertID() {
        return alertID;
    }

    public void setAlertID(int alertID) {
        this.alertID = alertID;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public double getWearHours() {
        return wearHours;
    }

    public void setWearHours(double wearHours) {
        this.wearHours = wearHours;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    
}
