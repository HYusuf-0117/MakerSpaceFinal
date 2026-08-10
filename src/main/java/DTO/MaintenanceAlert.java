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
    private String alertType;
    private String alertMessage;
    private boolean resolved;

    public MaintenanceAlert() {
    }

    public MaintenanceAlert(int equipmentId, String alertType, String alertMessage) {
        this.equipmentId = equipmentId;
        this.alertType = alertType;
        this.alertMessage = alertMessage;
        this.resolved = false;
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

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}