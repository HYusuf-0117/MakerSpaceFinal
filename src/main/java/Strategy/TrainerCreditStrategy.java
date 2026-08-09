/*
 * User Responsible: Owen Cabrera
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This strategy calculates credits for trainers who earn credits
 * by delivering training sessions to other members.
 */

package strategy;

import DTO.User;

/**
 * Credit calculation strategy for trainers.
 *
 * @author Owen Cabrera
 * @version 1.0
 */
public class TrainerCreditStrategy implements CreditCalculationStrategy {
    
    private static final double TRAINING_CREDIT_RATE = 10.0; // 10 credits per hour
    private static final double USAGE_DEBIT_RATE = 0.8; // 0.8 credits per hour (discount)
    
    @Override
    public double calculateCredits(User user, Activity activity) {
        if ("TRAINING".equals(activity.getActivityType())) {
            return activity.getTrainingHours() * TRAINING_CREDIT_RATE;
        }
        return 0.0;
    }
    
    @Override
    public double calculateDebits(User user, Usage usage) {
        // Trainers get a discount on equipment usage
        double totalDebits = 0.0;
        
        // Debit for equipment usage with discount
        totalDebits += usage.getHoursUsed() * USAGE_DEBIT_RATE;
        
        // Debit for materials consumed
        totalDebits += usage.getMaterialConsumed() * 0.04; // 20% discount on materials
        
        return totalDebits;
    }
}