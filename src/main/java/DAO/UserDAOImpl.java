/*
 * User Responsible: Hasbiya Yusuf
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This class implements the UserDAO interface using JDBC. It provides
 * database operations for creating, retrieving, updating, deleting, and
 * searching user records.
 */

package DAO;

import DAO.UserDAO;
import DTO.User;
import Database.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides JDBC implementation for User database operations.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public class UserDAOImpl implements UserDAO {

    private Connection connection;

    /**
     * Creates a UserDAOImpl object and connects to the database.
     *
     * @throws SQLException database error
     */
    public UserDAOImpl() throws SQLException {
        this.connection = DataSource.getInstance().getConnection();
    }

    /**
     * Creates a user record.
     *
     * @param u user to create
     * @throws SQLException database error
     */
    @Override
    public void create(User u) throws SQLException {
        String sql = "INSERT INTO User (first_name, last_name, email, password_hash, "
                + "user_type, credit_balance) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getFirstName());
            ps.setString(2, u.getLastName());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPasswordHash());
            ps.setString(5, u.getUserType());
            ps.setDouble(6, u.getCreditBalance());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    u.setUserId(keys.getInt(1));
                }
            }
        }
    }

    /**
     * Finds a user by ID.
     *
     * @param id user ID
     * @return user if found, otherwise null
     * @throws SQLException database error
     */
    @Override
    public User findById(int id) throws SQLException {
        String sql = "SELECT * FROM User WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    /**
     * Updates an existing user.
     *
     * @param u user to update
     * @throws SQLException database error
     */
    @Override
    public void update(User u) throws SQLException {
        String sql = "UPDATE User SET first_name = ?, last_name = ?, email = ?, "
                + "password_hash = ?, user_type = ?, credit_balance = ? WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, u.getFirstName());
            ps.setString(2, u.getLastName());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPasswordHash());
            ps.setString(5, u.getUserType());
            ps.setDouble(6, u.getCreditBalance());
            ps.setInt(7, u.getUserId());
            ps.executeUpdate();
        }
    }

    /**
     * Deletes a user by ID.
     *
     * @param id user ID
     * @throws SQLException database error
     */
    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM User WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /**
     * Retrieves all users.
     *
     * @return list of users
     * @throws SQLException database error
     */
    @Override
    public List<User> findAll() throws SQLException {
        String sql = "SELECT * FROM User";
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(mapRow(rs));
            }
        }
        return users;
    }

    /**
     * Finds a user by email.
     *
     * @param email user email
     * @return user if found, otherwise null
     * @throws SQLException database error
     */
    @Override
    public User findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM User WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    /**
     * Maps the current ResultSet row to a User DTO.
     *
     * @param rs result set containing user data
     * @return mapped User object
     * @throws SQLException database error
     */
    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User();
        u.setUserId(rs.getInt("user_id"));
        u.setFirstName(rs.getString("first_name"));
        u.setLastName(rs.getString("last_name"));
        u.setEmail(rs.getString("email"));
        u.setPasswordHash(rs.getString("password_hash"));
        u.setUserType(rs.getString("user_type"));
        u.setCreditBalance(rs.getDouble("credit_balance"));
        u.setCreatedAt(rs.getTimestamp("created_at"));
        return u;
    }
}