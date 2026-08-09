/*
 * User Responsible: Owen Cabrera
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This service class integrates the Strategy pattern with the DAO layer
 * to manage credits and debits for users.
 */

package business;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import DAO.LedgerEntryDAO;
import DAO.LedgerEntryDAOImpl;
import DTO.User;
import DTO.LedgerEntry;
import strategy.*;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Service class for managing credit and debit operations.
 *
 * @author Owen Cabrera
 * @version 1.0
 */
public class CreditService {
    
    private UserDAO userDAO;
    private LedgerEntryDAO ledgerDAO;
    private CreditCalculator creditCalculator;
    
    public CreditService() throws SQLException {
        this.userDAO = new UserDAOImpl();
        this.ledgerDAO = new LedgerEntryDAOImpl();
        this.creditCalculator = new CreditCalculator();
    }
    
    /**
     * Processes a credit-earning activity for a user.
     *
     * @param userId the user ID
     * @param activity the activity details
     * @return the amount of credits earned
     * @throws SQLException if a database error occurs
     */
    public double processCredits(int userId, Activity activity) throws SQLException {
        User user = userDAO.findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + userId);
        }
        
        // Calculate credits using the strategy
        double credits = creditCalculator.calculateCredits(user, activity);
        
        if (credits > 0) {
            // Update user's credit balance
            double newBalance = user.getCreditBalance() + credits;
            user.setCreditBalance(newBalance);
            userDAO.update(user);
            
            // Create ledger entry
            LedgerEntry entry = new LedgerEntry();
            entry.setUserId(userId);
            entry.setEntryType("CREDIT");
            entry.setAmount(credits);
            entry.setSourceType(activity.getActivityType());
            entry.setDescription("Credits earned from " + activity.getActivityType());
            entry.setEntryDate(new Timestamp(System.currentTimeMillis()));
            ledgerDAO.create(entry);
        }
        
        return credits;
    }
    
    /**
     * Processes debits for equipment usage.
     *
     * @param userId the user ID
     * @param usage the usage details
     * @return the amount of debits incurred
     * @throws SQLException if a database error occurs
     */
    public double processDebits(int userId, Usage usage) throws SQLException {
        User user = userDAO.findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + userId);
        }
        
        // Calculate debits using the strategy
        double debits = creditCalculator.calculateDebits(user, usage);
        
        if (debits > 0) {
            // Update user's credit balance (debits reduce credits)
            double newBalance = user.getCreditBalance() - debits;
            user.setCreditBalance(newBalance);
            userDAO.update(user);
            
            // Create ledger entry
            LedgerEntry entry = new LedgerEntry();
            entry.setUserId(userId);
            entry.setEntryType("DEBIT");
            entry.setAmount(debits);
            entry.setSourceType("EQUIPMENT_USAGE");
            entry.setDescription("Debit for equipment usage: " + usage.getHoursUsed() + " hours");
            entry.setEntryDate(new Timestamp(System.currentTimeMillis()));
            ledgerDAO.create(entry);
        }
        
        return debits;
    }
}
