package DAO;
import Database.DataSource;
import java.sql.PreparedStatement;
import DTO.MaintenanceAlert;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Auston Gurr
 */
public class MaintenanceAlertDAOImpl implements MaintenanceAlertDAO{
    private Connection connection;
    
    public MaintenanceAlertDAOImpl() throws SQLException {
        this.connection = DataSource.getInstance().getConnection();
    }
    
    @Override
    public void create(MaintenanceAlert alert) throws SQLException{
        String sql = "INSERT INTO Maintenance_Alert (equipment_id, alert_message, wear_hours, timestamp" 
                + "VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, alert.getEquipmentId());
            ps.setString(2, alert.getAlertMessage());
            ps.setDouble(3, alert.getWearHours());
            ps.setTimestamp(4, alert.getTimestamp());
            ps.executeUpdate();
            
            try (ResultSet keys = ps.getGeneratedKeys()){
                if (keys.next()){
                    alert.setAlertID(keys.getInt(1));
                }
            }
        }
    }
    
    @Override
    public MaintenanceAlert findById(int id) throws SQLException {
        String sql = "SELECT * FROM Maintenance_alert WHERE alert_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                return rs.next() ? mapRow(rs) : null;
            }
        }   
    }
    
    @Override
    public List<MaintenanceAlert> findAll() throws SQLException {
        String sql = "SELECT * FROM Maintenance_alert";
        List<MaintenanceAlert> alerts = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                alerts.add(mapRow(rs));
            }
        }
        return alerts;
    }
    
    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Maintenance_Alert WHERE alert_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
    
    public List<MaintenanceAlert> findByEquipmentId(int equipmentId) throws SQLException {
        String sql = "SELECT * FROM Maintenance_Alert WHERE equipment_id = ?";
        List<MaintenanceAlert> alerts = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, equipmentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    alerts.add(mapRow(rs));
                }
            }
        }
        return alerts;
    }
    
    private MaintenanceAlert mapRow(ResultSet rs) throws SQLException {
        MaintenanceAlert alert = new MaintenanceAlert();
        alert.setAlertID(rs.getInt("alert_id"));
        alert.setEquipmentId(rs.getInt("equipment_id"));
        alert.setAlertMessage(rs.getString("alert_message"));
        alert.setWearHours(rs.getDouble("wear_hours"));
        alert.setTimestamp(rs.getTimestamp("timestamp"));
        return alert;
    }
    
    @Override
public void update(MaintenanceAlert alert) throws SQLException {
    String sql = "UPDATE Maintenance_Alert SET equipment_id = ?, alert_message = ?, wear_hours = ?, timestamp = ? "
               + "WHERE alert_id = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, alert.getEquipmentId());
        ps.setString(2, alert.getAlertMessage());
        ps.setDouble(3, alert.getWearHours());
        ps.setTimestamp(4, alert.getTimestamp());
        ps.setInt(5, alert.getAlertID());
        ps.executeUpdate();
    }
}

}
