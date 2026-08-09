package SimpleFactory;

import DTO.Equipment;

/**
 * Creates Equipment objects based on the requested equipment type.
 *
 * @author Chance Boukoro Bakala
 * @version 1.0
 */
public class EquipmentFactory {

    /**
     * Creates the correct Equipment subtype.
     *
     * @param type equipment type
     * @param equipmentId unique equipment ID
     * @param assetTag equipment asset tag
     * @param make manufacturer
     * @param model equipment model
     * @param status current equipment status
     * @param hourlyRate hourly usage rate
     * @param usageHours total usage hours
     * @param maintenanceThreshold maintenance threshold
     * @return the created Equipment object
     */
    public static Equipment createEquipment(
            String type,
            int equipmentId,
            String assetTag,
            String make,
            String model,
            String status,
            double hourlyRate,
            double usageHours,
            double maintenanceThreshold) {

        switch (type.toLowerCase()) {

            case "3d printer":
                return new Printer3D(
                        equipmentId,
                        assetTag,
                        make,
                        model,
                        status,
                        hourlyRate,
                        usageHours,
                        maintenanceThreshold
                );

            case "laser cutter":
                return new LaserCutter(
                        equipmentId,
                        assetTag,
                        make,
                        model,
                        status,
                        hourlyRate,
                        usageHours,
                        maintenanceThreshold
                );

            case "cnc machine":
                return new CNCMachine(
                        equipmentId,
                        assetTag,
                        make,
                        model,
                        status,
                        hourlyRate,
                        usageHours,
                        maintenanceThreshold
                );

            default:
                throw new IllegalArgumentException(
                        "Unknown equipment type: " + type
                );
        }
    }
} 
