/*
 * User Responsible: Hasbiya Yusuf
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This interface defines database operations specific to equipment records.
 * It extends the GenericDAO interface and provides methods for retrieving
 * equipment based on application requirements.
 */

package DAO;

import DTO.Equipment;
import java.sql.SQLException;
import java.util.List;

/**
 * Defines data access operations for Equipment objects.
 *
 * This interface extends the GenericDAO interface and declares methods
 * used to retrieve and manage equipment records in the database.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public interface EquipmentDAO extends GenericDAO<Equipment> {

    /**
     * Retrieves all equipment with the specified status.
     *
     * @param status the equipment status to search for
     * @return a list of equipment matching the specified status
     * @throws SQLException if a database access error occurs
     */
    List<Equipment> findByStatus(String status) throws SQLException;
}