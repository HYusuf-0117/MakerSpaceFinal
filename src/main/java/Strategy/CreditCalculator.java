/*
 * User Responsible: Owen Cabrera
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This class is the Context for the Strategy pattern. It manages the
 * selection and execution of credit calculation strategies based on
 * user type.
 */

package strategy;

import DTO.User;

/**
 * Context class that manages credit calculation strategies.
 *
 * @author Owen Cabrera
 * @version 1.0
 */
public class CreditCalculator {
    private CreditCalculationStrategy strategy;
    
    /**
     * Sets the strategy based on user type.
     *
     * @param user the user whose strategy should be used
     */
    public void setStrategy(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        
        String userType = user.getUserType();
        
        if ("Trainer".equalsIgnoreCase(userType)) {
            this.strategy = new TrainerCreditStrategy();
        } else if ("Shop-Tech".equalsIgnoreCase(userType)) {
            this.strategy = new ShopTechCreditStrategy();
        } else {
            this.strategy = new UserCreditStrategy();
        }
    }
    
    /**
     * Sets a specific strategy directly.
     *
     * @param strategy the strategy to use
     */
    public void setStrategy(CreditCalculationStrategy strategy) {
        this.strategy = strategy;
    }
    
    /**
     * Calculates credits for a user's activity.
     *
     * @param user the user performing the activity
     * @param activity the activity details
     * @return the amount of credits earned
     */
    public double calculateCredits(User user, Activity activity) {
        if (strategy == null) {
            setStrategy(user);
        }
        return strategy.calculateCredits(user, activity);
    }
    
    /**
     * Calculates debits for a user's equipment usage.
     *
     * @param user the user using the equipment
     * @param usage the usage details
     * @return the amount of debits incurred
     */
    public double calculateDebits(User user, Usage usage) {
        if (strategy == null) {
            setStrategy(user);
        }
        return strategy.calculateDebits(user, usage);
    }
}