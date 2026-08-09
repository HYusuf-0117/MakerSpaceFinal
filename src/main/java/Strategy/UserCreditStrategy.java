/*
 * User Responsible: Owen Cabrera
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This strategy calculates credits for regular users who earn credits
 * primarily through donating materials to the co-op.
 */

package strategy;

import DTO.User;

/**
 * Credit calculation strategy for regular users.
 *
 * @author Owen Cabrera
 * @version 1.0
 */
public class UserCreditStrategy implements CreditCalculationStrategy {
    
    private static final double DONATION_CREDIT_RATE = 0.5; // 50% of donation value
    private static final double USAGE_DEBIT_RATE = 1.0; // 1 credit per hour
    
    @Override
    public double calculateCredits(User user, Activity activity) {
        if ("DONATION".equals(activity.getActivityType())) {
            return activity.getDonationValue() * DONATION_CREDIT_RATE;
        }
        return 0.0;
    }
    
    @Override
    public double calculateDebits(User user, Usage usage) {
        double totalDebits = 0.0;
        
        // Debit for equipment usage
        totalDebits += usage.getHoursUsed() * USAGE_DEBIT_RATE;
        
        // Debit for materials consumed
        totalDebits += usage.getMaterialConsumed() * 0.05; // $0.05 per gram/unit
        
        return totalDebits;
    }
}