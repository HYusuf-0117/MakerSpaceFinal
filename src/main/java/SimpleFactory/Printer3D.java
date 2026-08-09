package SimpleFactory;

import DTO.Equipment;

/**
 * Represents a 3D printer in the Maker Space system.
 *
 * @author Chance Boukoro Bakala
 * @version 1.0
 */
public class Printer3D extends Equipment {

    /**
     * Creates a new 3D printer.
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
    public Printer3D(int equipmentId, String assetTag, String make, String model,
                     String status, double hourlyRate, double usageHours,
                     double maintenanceThreshold) {

        super(
                equipmentId,
                assetTag,
                make,
                model,
                "3D Printer",
                status,
                hourlyRate,
                usageHours,
                maintenanceThreshold
        );
    }
}