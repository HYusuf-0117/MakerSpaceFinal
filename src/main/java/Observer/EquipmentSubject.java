package Observer;

import java.util.ArrayList;
import java.util.List;
import DTO.Equipment;
import DAO.EquipmentDAO;
import java.sql.SQLException;
/**
 * Observer Subject that monitors equipment usage and triggers maintenance alerts.
 * @author Auston Gurr
 */
public class EquipmentSubject {
    private Equipment equipmentDTO;
    private EquipmentDAO equipmentDAO;
    
    private List<MaintenanceObserver> observers = new ArrayList<>();
    
    public EquipmentSubject(Equipment equipmentDTO, EquipmentDAO equipmentDAO){
        this.equipmentDTO = equipmentDTO;
        this.equipmentDAO = equipmentDAO;
    }
    
    public void addObserver(MaintenanceObserver observer){
        observers.add(observer);
    }
    
    public void removeObserver(MaintenanceObserver observer) {
        observers.remove(observer);
    }
    
    /**
     * adds usage hours to the equipment and calls checkThreshold().
     * @param hoursUsed
     * @throws SQLException 
     */
    public void addUsage(double hoursUsed) throws SQLException {
        double newUsage = equipmentDTO.getUsageHours() + hoursUsed;
        equipmentDTO.setUsageHours(newUsage);
        
        equipmentDAO.updateUsageHours(equipmentDTO.getEquipmentId(), newUsage);
        
        checkThreshold();
    }
    
    /**
     * checks if equipment has crossed its maintenance threshold
     * @throws SQLException 
     */
    private void checkThreshold() throws SQLException {
        if (equipmentDTO.getUsageHours() >= equipmentDTO.getMaintenanceThreshold() && !"down_for_maintenance".equals(equipmentDTO.getStatus())){
            //update status in DTO and DB
            equipmentDTO.setStatus("down_for_maintenance");
            equipmentDAO.updateStatus(equipmentDTO.getEquipmentId(), "down_for_maintenance");
            //Alert Creation
            MaintenanceAlert alert = new MaintenanceAlert(equipmentDTO, equipmentDTO.getUsageHours());
            notifyObservers(alert);
        }
    }
    /**
     * notifies all registered observers.
     * @param alert 
     */
    private void notifyObservers(MaintenanceAlert alert){
        for (MaintenanceObserver observer : observers){
            observer.onMaintenanceThresholdCrossed(alert);
        }
    }
}
