package DAO;

import DTO.EquipmentSession;
import Database.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of EquipmentSessionDAO.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public class EquipmentSessionDAOImpl implements EquipmentSessionDAO {

    private final Connection connection;

    /**
     * Creates an EquipmentSessionDAOImpl and connects to the database.
     *
     * @throws SQLException if a database connection error occurs
     */
    public EquipmentSessionDAOImpl() throws SQLException {
        connection = DataSource.getInstance().getConnection();
    }

    /**
     * Creates a new equipment session.
     *
     * @param session the equipment session
     * @throws SQLException if a database error occurs
     */
    @Override
    public void create(EquipmentSession session) throws SQLException {
        String sql = "INSERT INTO Equipment_Session "
                + "(user_id, equipment_id, consumable_id, check_in_time, "
                + "material_quantity, total_debit) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, session.getUserId());
            ps.setInt(2, session.getEquipmentId());

            if (session.getConsumableId() == null) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, session.getConsumableId());
            }

            ps.setTimestamp(4, session.getCheckInTime());
            ps.setDouble(5, session.getMaterialQuantity());
            ps.setDouble(6, session.getTotalDebit());

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    session.setSessionId(keys.getInt(1));
                }
            }
        }
    }

    /**
     * Finds an equipment session by ID.
     *
     * @param id the session ID
     * @return the equipment session, or null if not found
     * @throws SQLException if a database error occurs
     */
    @Override
    public EquipmentSession findById(int id) throws SQLException {
        String sql = "SELECT * FROM Equipment_Session "
                + "WHERE session_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    /**
     * Retrieves all equipment sessions.
     *
     * @return list of equipment sessions
     * @throws SQLException if a database error occurs
     */
    @Override
    public List findAll() throws SQLException {
        String sql = "SELECT * FROM Equipment_Session "
                + "ORDER BY check_in_time DESC";

        List sessions = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                sessions.add(mapRow(rs));
            }
        }

        return sessions;
    }

    /**
     * Finds active sessions for equipment.
     *
     * @param equipmentId the equipment ID
     * @return list of active sessions
     * @throws SQLException if a database error occurs
     */
    @Override
    public List findActiveByEquipment(int equipmentId)
            throws SQLException {
        String sql = "SELECT * FROM Equipment_Session "
                + "WHERE equipment_id = ? "
                + "AND check_out_time IS NULL";

        List sessions = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, equipmentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sessions.add(mapRow(rs));
                }
            }
        }

        return sessions;
    }

    /**
     * Finds active sessions for a user.
     *
     * @param userId the user ID
     * @return list of active sessions
     * @throws SQLException if a database error occurs
     */
    @Override
    public List findActiveByUser(int userId)
            throws SQLException {
        String sql = "SELECT * FROM Equipment_Session "
                + "WHERE user_id = ? "
                + "AND check_out_time IS NULL";

        List sessions = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sessions.add(mapRow(rs));
                }
            }
        }

        return sessions;
    }

    /**
     * Checks out an equipment session.
     *
     * @param sessionId the session ID
     * @param checkOutTime the checkout time
     * @param materialQuantity the material consumed
     * @param totalDebit the total debit
     * @throws SQLException if a database error occurs
     */
    @Override
    public void checkOut(
            int sessionId,
            java.sql.Timestamp checkOutTime,
            double materialQuantity,
            double totalDebit)
            throws SQLException {
        String sql = "UPDATE Equipment_Session "
                + "SET check_out_time = ?, "
                + "material_quantity = ?, "
                + "total_debit = ? "
                + "WHERE session_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setTimestamp(1, checkOutTime);
            ps.setDouble(2, materialQuantity);
            ps.setDouble(3, totalDebit);
            ps.setInt(4, sessionId);

            ps.executeUpdate();
        }
    }

    /**
     * Maps a database row to an EquipmentSession object.
     *
     * @param rs the result set containing session data
     * @return the populated equipment session
     * @throws SQLException if a database access error occurs
     */
    private EquipmentSession mapRow(ResultSet rs)
            throws SQLException {
        EquipmentSession session = new EquipmentSession();

        session.setSessionId(rs.getInt("session_id"));
        session.setUserId(rs.getInt("user_id"));
        session.setEquipmentId(rs.getInt("equipment_id"));

        int consumableId = rs.getInt("consumable_id");
        session.setConsumableId(
                rs.wasNull() ? null : consumableId
        );

        session.setCheckInTime(
                rs.getTimestamp("check_in_time")
        );
        session.setCheckOutTime(
                rs.getTimestamp("check_out_time")
        );
        session.setMaterialQuantity(
                rs.getDouble("material_quantity")
        );
        session.setTotalDebit(
                rs.getDouble("total_debit")
        );

        return session;
    }
}