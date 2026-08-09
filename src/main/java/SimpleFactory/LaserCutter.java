package SimpleFactory;

import DTO.Equipment;

/**
 * Represents a laser cutter in the Maker Space system.
 *
 * @author Chance Boukoro Bakala
 * @version 1.0
 */
public class LaserCutter extends Equipment {

    /**
     * Creates a new laser cutter.
     *
     * @param equipmentId unique equipment ID
     * @param assetTag equipment asset tag
     * @param make manufacturer
     * @param model equipment model
     * @param status current equipment status
     * @param hourlyRate hourly usage rate
     * @param usageHours total usage hours
     * @param maintenanceThreshold maintenance threshold
     */
    public LaserCutter(int equipmentId, String assetTag, String make, String model,
                       String status, double hourlyRate, double usageHours,
                       double maintenanceThreshold) {

        super(
                equipmentId,
                assetTag,
                make,
                model,
                "Laser Cutter",
                status,
                hourlyRate,
                usageHours,
                maintenanceThreshold
        );
    }
} 
