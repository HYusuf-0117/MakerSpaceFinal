/*
 * User Responsible: Owen Cabrera
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This strategy calculates credits for Shop-Techs who earn credits
 * through maintenance work and completing work orders.
 */

package strategy;

import DTO.User;

/**
 * Credit calculation strategy for shop technicians.
 *
 * @author Owen Cabrera
 * @version 1.0
 */
public class ShopTechCreditStrategy implements CreditCalculationStrategy {
    
    private static final double MAINTENANCE_CREDIT_RATE = 15.0; // 15 credits per hour
    private static final double WORK_ORDER_CREDIT_RATE = 20.0; // 20 credits per work order
    private static final double USAGE_DEBIT_RATE = 0.5; // 0.5 credits per hour (highest discount)
    
    @Override
    public double calculateCredits(User user, Activity activity) {
        double credits = 0.0;
        
        if ("MAINTENANCE".equals(activity.getActivityType())) {
            credits += activity.getMaintenanceHours() * MAINTENANCE_CREDIT_RATE;
        }
        
        if ("WORK_ORDER".equals(activity.getActivityType())) {
            credits += activity.getWorkOrdersCompleted() * WORK_ORDER_CREDIT_RATE;
        }
        
        return credits;
    }
    
    @Override
    public double calculateDebits(User user, Usage usage) {
        // Shop-Techs get the highest discount on equipment usage
        double totalDebits = 0.0;
        
        // Debit for equipment usage with discount
        totalDebits += usage.getHoursUsed() * USAGE_DEBIT_RATE;
        
        // Debit for materials consumed (biggest discount)
        totalDebits += usage.getMaterialConsumed() * 0.03; // 40% discount on materials
        
        return totalDebits;
    }
}