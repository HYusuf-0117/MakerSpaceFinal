package Observer;
import DTO.Equipment;
/**
 * Represents Maintenance alert, triggered when equipment crosses its threshold.
 * @author Auston Gurr
 */
public class MaintenanceAlertEvent {
    
    private Equipment equipment;
    private double wearHours;
    private String message;
    
    public MaintenanceAlertEvent(Equipment equipment, double wearHours){
        this.equipment = equipment;
        this.wearHours = wearHours;
        this.message = "Maintenance threshold crossed for equipment: " + equipment.getAssetTag();
    }
    public Equipment getEquipment(){
        return equipment;
    }
    public double getWearHours(){
        return wearHours;
    }
    public String getMessage(){
        return message;
    }
}
