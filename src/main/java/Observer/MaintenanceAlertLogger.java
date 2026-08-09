package Observer;
import DAO.MaintenanceAlertDAO;
import DTO.MaintenanceAlert;
import java.sql.Timestamp;
/**
 * Observer that logs maintenance alerts to the database.
 * @author Auston Gurr
 */
public class MaintenanceAlertLogger implements MaintenanceObserver {
    
    private MaintenanceAlertDAO alertDAO;

    public MaintenanceAlertLogger(MaintenanceAlertDAO alertDAO) {
        this.alertDAO = alertDAO;
    }
    
    @Override
    public void onMaintenanceThresholdCrossed(MaintenanceAlertEvent alert){
        try {
            MaintenanceAlert dto = new MaintenanceAlert(
            alert.getEquipment().getEquipmentId(),
            alert.getMessage(),
            alert.getWearHours(),
            new Timestamp(System.currentTimeMillis())
            );
            alertDAO.create(dto);
            
            System.out.println("Maintenance alert saved for equipment: " + alert.getEquipment().getAssetTag());
        } catch (Exception e) {
            System.err.println("Failed to save maintenance alert: " + e.getMessage());
        }
    }
    
}
