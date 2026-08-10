package DAO;

import Builder.WorkOrder;
import java.sql.SQLException;
import java.util.List;

/**
 * Defines database operations for WorkOrder objects.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public interface WorkOrderDAO {

    /**
     * Creates a new work order.
     *
     * @param workOrder the work order to create
     * @throws SQLException if a database error occurs
     */
    void create(WorkOrder workOrder) throws SQLException;

    /**
     * Finds a work order by ID.
     *
     * @param id the work order ID
     * @return the work order, or null if not found
     * @throws SQLException if a database error occurs
     */
    WorkOrder findById(int id) throws SQLException;

    /**
     * Retrieves all work orders.
     *
     * @return list of work orders
     * @throws SQLException if a database error occurs
     */
    List<WorkOrder> findAll() throws SQLException;
}