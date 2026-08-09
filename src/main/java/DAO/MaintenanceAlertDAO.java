package DAO;
import DTO.MaintenanceAlert;
import java.sql.SQLException;
import java.util.List;
/**
 * This interface extends the GenericDAO interface and declares methods
 * used to retrieve and manage Maintenance ALert records in the database.
 * @author Auston Gurr
 */
public interface MaintenanceAlertDAO extends GenericDAO<MaintenanceAlert>{
    List<MaintenanceAlert> findByEquipmentId(int equipmentId) throws SQLException;
    
}
