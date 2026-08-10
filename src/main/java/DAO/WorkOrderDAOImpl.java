package DAO;

import Builder.WorkOrder;
import Database.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of WorkOrderDAO.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public class WorkOrderDAOImpl implements WorkOrderDAO {

    private final Connection connection;

    /**
     * Creates a WorkOrderDAOImpl and obtains a database connection.
     *
     * @throws SQLException if a database connection cannot be obtained
     */
    public WorkOrderDAOImpl() throws SQLException {
        this.connection = DataSource.getInstance().getConnection();
    }

    /**
     * Creates a work order in the database.
     *
     * @param workOrder the work order to save
     * @throws SQLException if a database error occurs
     */
    @Override
    public void create(WorkOrder workOrder) throws SQLException {
        String sql = "INSERT INTO Work_Order "
                + "(member_id, description, priority, status) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, workOrder.getRequestorId());
            ps.setString(2, workOrder.getDescription());

            // Convert Builder priority to database ENUM value
            ps.setString(3, convertPriority(workOrder.getPriority()));

            // Convert Builder status to database ENUM value
            ps.setString(4, convertStatus(workOrder.getStatus()));

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    workOrder.setWorkOrderId(keys.getInt(1));
                }
            }
        }
    }

    /**
     * Finds a work order by ID.
     *
     * @param id the work order ID
     * @return the work order, or null if not found
     * @throws SQLException if a database error occurs
     */
    @Override
    public WorkOrder findById(int id) throws SQLException {
        String sql = "SELECT * FROM Work_Order WHERE work_order_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }

                return null;
            }
        }
    }

    /**
     * Retrieves all work orders.
     *
     * @return list of work orders
     * @throws SQLException if a database error occurs
     */
    @Override
    public List findAll() throws SQLException {
        String sql = "SELECT * FROM Work_Order";
        List workOrders = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                workOrders.add(mapRow(rs));
            }
        }

        return workOrders;
    }

    /**
     * Maps a database row to a WorkOrder object.
     *
     * @param rs result set containing work order data
     * @return populated WorkOrder object
     * @throws SQLException if a database error occurs
     */
    private WorkOrder mapRow(ResultSet rs) throws SQLException {
        WorkOrder workOrder = new WorkOrder();

        workOrder.setWorkOrderId(
                rs.getInt("work_order_id")
        );
        workOrder.setRequestorId(
                rs.getInt("member_id")
        );
        workOrder.setDescription(
                rs.getString("description")
        );
        workOrder.setPriority(
                rs.getString("priority")
        );
        workOrder.setStatus(
                rs.getString("status")
        );

        return workOrder;
    }

    /**
     * Converts Builder priority values to database ENUM values.
     *
     * @param priority Builder priority
     * @return database-compatible priority
     */
    private String convertPriority(String priority) {
        if (priority == null) {
            return "Medium";
        }

        switch (priority.toUpperCase()) {
            case "LOW":
                return "Low";

            case "HIGH":
                return "High";

            case "NORMAL":
            case "MEDIUM":
            default:
                return "Medium";
        }
    }

    /**
     * Converts Builder status values to database ENUM values.
     *
     * @param status Builder status
     * @return database-compatible status
     */
    private String convertStatus(String status) {
        if (status == null) {
            return "Submitted";
        }

        switch (status.toUpperCase()) {
            case "COMPLETED":
                return "Completed";

            case "IN-PROGRESS":
            case "IN_PROGRESS":
            case "IN PROGRESS":
                return "In-Progress";

            case "PENDING":
            case "SUBMITTED":
            default:
                return "Submitted";
        }
    }
}