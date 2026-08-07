/*
 * User Responsible: Hasbiya Yusuf
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This class implements the EquipmentDAO interface using JDBC. It provides
 * database operations for creating, retrieving, updating, deleting, and
 * searching equipment records in the Equipment table.
 */

package DAO;

import DAO.EquipmentDAO;
import DTO.Equipment;
import Database.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides JDBC implementation of the EquipmentDAO interface.
 *
 * This class manages database communication for Equipment objects and
 * performs CRUD operations using SQL queries.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public abstract class EquipmentDAOImpl implements EquipmentDAO {

    private Connection connection;

    /**
     * Creates an EquipmentDAOImpl object and connects to the database.
     *
     * @throws SQLException if a database connection error occurs
     */
  
    public EquipmentDAOImpl() throws SQLException {
        this.connection = DataSource.getInstance().getConnection();
    }

    /**
     * Creates a new equipment record in the database.
     *
     * @param e the Equipment object to insert
     * @throws SQLException if a database access error occurs
     */
    
    public void create(Equipment e) throws SQLException {
        String sql = "INSERT INTO Equipment (asset_tag, make, model, category, status, "
                + "hourly_rate, usage_hours, maintenance_threshold) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getAssetTag());
            ps.setString(2, e.getMake());
            ps.setString(3, e.getModel());
            ps.setString(4, e.getCategory());
            ps.setString(5, e.getStatus());
            ps.setDouble(6, e.getHourlyRate());
            ps.setDouble(7, e.getUsageHours());
            ps.setDouble(8, e.getMaintenanceThreshold());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    e.setEquipmentId(keys.getInt(1));
                }
            }
        }
    }

    /**
     * Finds equipment by its unique identifier.
     *
     * @param id the equipment ID
     * @return the Equipment object if found, otherwise null
     * @throws SQLException if a database access error occurs
     */
    @Override
    public Equipment findById(int id) throws SQLException {
        String sql = "SELECT * FROM Equipment WHERE equipment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    /**
     * Updates an existing equipment record.
     *
     * @param e the Equipment object containing updated information
     * @throws SQLException if a database access error occurs
     */

    public void update(Equipment e) throws SQLException {
        String sql = "UPDATE Equipment SET asset_tag = ?, make = ?, model = ?, category = ?, "
                + "status = ?, hourly_rate = ?, usage_hours = ?, maintenance_threshold = ? "
                + "WHERE equipment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, e.getAssetTag());
            ps.setString(2, e.getMake());
            ps.setString(3, e.getModel());
            ps.setString(4, e.getCategory());
            ps.setString(5, e.getStatus());
            ps.setDouble(6, e.getHourlyRate());
            ps.setDouble(7, e.getUsageHours());
            ps.setDouble(8, e.getMaintenanceThreshold());
            ps.setInt(9, e.getEquipmentId());
            ps.executeUpdate();
        }
    }

    /**
     * Deletes an equipment record from the database.
     *
     * @param id the equipment ID to delete
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Equipment WHERE equipment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /**
     * Retrieves all equipment records from the database.
     *
     * @return a list containing all equipment records
     * @throws SQLException if a database access error occurs
     */
    @Override
    public List<Equipment> findAll() throws SQLException {
        String sql = "SELECT * FROM Equipment";
        List<Equipment> equipmentList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                equipmentList.add(mapRow(rs));
            }
        }
        return equipmentList;
    }

    /**
     * Retrieves all equipment records with the specified status.
     *
     * @param status the equipment status to search for
     * @return a list of equipment matching the status
     * @throws SQLException if a database access error occurs
     */
    @Override
    public List<Equipment> findByStatus(String status) throws SQLException {
        String sql = "SELECT * FROM Equipment WHERE status = ?";
        List<Equipment> equipmentList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    equipmentList.add(mapRow(rs));
                }
            }
        }
        return equipmentList;
    }

    /**
     * Maps the current row of an Equipment ResultSet onto an Equipment DTO.
     *
     * @param rs the ResultSet containing equipment data
     * @return an Equipment object populated with database values
     * @throws SQLException if a database access error occurs
     */
    private Equipment mapRow(ResultSet rs) throws SQLException {
        Equipment e = new Equipment();
        e.setEquipmentId(rs.getInt("equipment_id"));
        e.setAssetTag(rs.getString("asset_tag"));
        e.setMake(rs.getString("make"));
        e.setModel(rs.getString("model"));
        e.setCategory(rs.getString("category"));
        e.setStatus(rs.getString("status"));
        e.setHourlyRate(rs.getDouble("hourly_rate"));
        e.setUsageHours(rs.getDouble("usage_hours"));
        e.setMaintenanceThreshold(rs.getDouble("maintenance_threshold"));
        return e;
    }
}