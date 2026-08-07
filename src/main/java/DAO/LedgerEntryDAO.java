/*
 * User Responsible: Hasbiya Yusuf
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This interface defines database operations specific to ledger entries.
 * It extends the GenericDAO contract and provides ledger entry retrieval
 * functionality for the application.
 */

package DAO;

import DTO.LedgerEntry;
import java.sql.SQLException;
import java.util.List;

/**
 * Defines data access operations for LedgerEntry objects.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public interface LedgerEntryDAO extends GenericDAO<LedgerEntry> {

    /**
     * Retrieves ledger entries for a user.
     *
     * @param userId user ID
     * @return list of ledger entries
     * @throws SQLException database error
     */
    List<LedgerEntry> findByUser(int userId) throws SQLException;
}