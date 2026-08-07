/*
 * User Responsible: Hasbiya Yusuf
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This class implements the LedgerEntryDAO interface using JDBC. It provides
 * database operations for creating, retrieving, updating, deleting, and
 * searching ledger entry records.
 */

package DAO;

import DAO.LedgerEntryDAO;
import DTO.LedgerEntry;
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
 * Provides JDBC implementation for LedgerEntry database operations.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public class LedgerEntryDAOImpl implements LedgerEntryDAO {

    private Connection connection;

    /**
     * Creates a LedgerEntryDAOImpl object and connects to the database.
     *
     * @throws SQLException database error
     */
    public LedgerEntryDAOImpl() throws SQLException {
        this.connection = DataSource.getInstance().getConnection();
    }

    /**
     * Creates a ledger entry.
     *
     * @param entry ledger entry to create
     * @throws SQLException database error
     */
    @Override
    public void create(LedgerEntry entry) throws SQLException {
        String sql = "INSERT INTO Ledger_Entry (user_id, entry_type, amount, source_type, "
                + "source_id, description, entry_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, entry.getUserId());
            ps.setString(2, entry.getEntryType());
            ps.setDouble(3, entry.getAmount());
            ps.setString(4, entry.getSourceType());
            setNullableInt(ps, 5, entry.getSourceId());
            ps.setString(6, entry.getDescription());
            ps.setTimestamp(7, entry.getEntryDate());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    entry.setLedgerId(keys.getInt(1));
                }
            }
        }
    }

    /**
     * Finds a ledger entry by ID.
     *
     * @param id ledger entry ID
     * @return ledger entry if found, otherwise null
     * @throws SQLException database error
     */
    @Override
    public LedgerEntry findById(int id) throws SQLException {
        String sql = "SELECT * FROM Ledger_Entry WHERE ledger_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    /**
     * Updates a ledger entry.
     *
     * @param entry ledger entry to update
     * @throws SQLException database error
     */
    @Override
    public void update(LedgerEntry entry) throws SQLException {
        String sql = "UPDATE Ledger_Entry SET user_id = ?, entry_type = ?, amount = ?, "
                + "source_type = ?, source_id = ?, description = ?, entry_date = ? "
                + "WHERE ledger_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, entry.getUserId());
            ps.setString(2, entry.getEntryType());
            ps.setDouble(3, entry.getAmount());
            ps.setString(4, entry.getSourceType());
            setNullableInt(ps, 5, entry.getSourceId());
            ps.setString(6, entry.getDescription());
            ps.setTimestamp(7, entry.getEntryDate());
            ps.setInt(8, entry.getLedgerId());
            ps.executeUpdate();
        }
    }

    /**
     * Deletes a ledger entry by ID.
     *
     * @param id ledger entry ID
     * @throws SQLException database error
     */
    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Ledger_Entry WHERE ledger_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /**
     * Retrieves all ledger entries.
     *
     * @return list of ledger entries
     * @throws SQLException database error
     */
    @Override
    public List<LedgerEntry> findAll() throws SQLException {
        String sql = "SELECT * FROM Ledger_Entry";
        List<LedgerEntry> entries = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                entries.add(mapRow(rs));
            }
        }
        return entries;
    }

    /**
     * Retrieves ledger entries for a user.
     *
     * @param userId user ID
     * @return list of ledger entries
     * @throws SQLException database error
     */
    @Override
    public List<LedgerEntry> findByUser(int userId) throws SQLException {
        String sql = "SELECT * FROM Ledger_Entry WHERE user_id = ? ORDER BY entry_date DESC";
        List<LedgerEntry> entries = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    entries.add(mapRow(rs));
                }
            }
        }
        return entries;
    }

    /**
     * Sets a nullable integer value in a prepared statement.
     *
     * @param ps prepared statement
     * @param index parameter index
     * @param value integer value
     * @throws SQLException database error
     */
    private void setNullableInt(PreparedStatement ps, int index, Integer value) throws SQLException {
        if (value == null) {
            ps.setNull(index, Types.INTEGER);
        } else {
            ps.setInt(index, value);
        }
    }

    /**
     * Maps a ResultSet row to a LedgerEntry object.
     *
     * @param rs result set containing ledger data
     * @return mapped LedgerEntry object
     * @throws SQLException database error
     */
    private LedgerEntry mapRow(ResultSet rs) throws SQLException {
        LedgerEntry entry = new LedgerEntry();
        entry.setLedgerId(rs.getInt("ledger_id"));
        entry.setUserId(rs.getInt("user_id"));
        entry.setEntryType(rs.getString("entry_type"));
        entry.setAmount(rs.getDouble("amount"));
        entry.setSourceType(rs.getString("source_type"));
        int sourceId = rs.getInt("source_id");
        entry.setSourceId(rs.wasNull() ? null : sourceId);
        entry.setDescription(rs.getString("description"));
        entry.setEntryDate(rs.getTimestamp("entry_date"));
        return entry;
    }
}