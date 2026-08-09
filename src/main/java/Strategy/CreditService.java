/*
 * User Responsible: Owen Cabrera
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This interface defines the strategy contract for calculating credits
 * earned by different user types in the Maker Space system.
 */

package strategy;

import DTO.User;

/**
 * Defines the strategy for calculating credits based on user type and activity.
 *
 * @author Owen Cabrera
 * @version 1.0
 */
public interface CreditCalculationStrategy {
    
    /**
     * Calculates credits earned from an activity.
     *
     * @param user the user performing the activity
     * @param activity the activity details
     * @return the amount of credits earned
     */
    double calculateCredits(User user, Activity activity);
    
    /**
     * Calculates debits incurred from using equipment or materials.
     *
     * @param user the user consuming resources
     * @param usage the usage details
     * @return the amount of debits incurred
     */
    double calculateDebits(User user, Usage usage);
}
