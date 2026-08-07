/*
 * User Responsible: Hasbiya Yusuf
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This interface defines database operations specific to user records.
 * It extends the GenericDAO contract and provides user-specific data
 * retrieval functionality.
 */

package DAO;

import DTO.User;
import java.sql.SQLException;

/**
 * Defines data access operations for User objects.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public interface UserDAO extends GenericDAO<User> {

    /**
     * Finds a user by email address.
     *
     * @param email user email address
     * @return user if found, otherwise null
     * @throws SQLException database error
     */
    User findByEmail(String email) throws SQLException;
}