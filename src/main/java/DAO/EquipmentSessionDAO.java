package DAO;

import DTO.EquipmentSession;
import java.sql.SQLException;
import java.util.List;

/**
 * Defines database operations for equipment sessions.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public interface EquipmentSessionDAO {

    /**
     * Creates a new equipment session.
     *
     * @param session the equipment session
     * @throws SQLException if a database error occurs
     */
    void create(EquipmentSession session) throws SQLException;

    /**
     * Finds an equipment session by ID.
     *
     * @param id the session ID
     * @return the equipment session, or null if not found
     * @throws SQLException if a database error occurs
     */
    EquipmentSession findById(int id) throws SQLException;

    /**
     * Retrieves all equipment sessions.
     *
     * @return list of equipment sessions
     * @throws SQLException if a database error occurs
     */
    List findAll() throws SQLException;

    /**
     * Finds active sessions for equipment.
     *
     * @param equipmentId the equipment ID
     * @return list of active sessions
     * @throws SQLException if a database error occurs
     */
    List findActiveByEquipment(int equipmentId) throws SQLException;

    /**
     * Finds active sessions for a user.
     *
     * @param userId the user ID
     * @return list of active sessions
     * @throws SQLException if a database error occurs
     */
    List findActiveByUser(int userId) throws SQLException;

    /**
     * Checks out an equipment session.
     *
     * @param sessionId the session ID
     * @param checkOutTime the checkout time
     * @param materialQuantity the material consumed
     * @param totalDebit the total debit
     * @throws SQLException if a database error occurs
     */
    void checkOut(
        int sessionId,
        java.sql.Timestamp checkOutTime,
        double materialQuantity,
        double totalDebit
    ) throws SQLException;
}