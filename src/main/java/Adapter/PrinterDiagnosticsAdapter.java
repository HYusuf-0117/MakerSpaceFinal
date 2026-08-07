/*
 * User Responsible: Hasbiya Yusuf
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This class implements the Adapter pattern by converting the
 * PrinterVendorAPI functionality into the application's EquipmentDiagnostics
 * interface. It allows the business layer to access equipment diagnostics
 * without depending on vendor-specific APIs.
 */

package Adapter;

/**
 * Adapts PrinterVendorAPI to the EquipmentDiagnostics interface.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public class PrinterDiagnosticsAdapter implements EquipmentDiagnostics {

    private PrinterVendorAPI vendorApi;

    /**
     * Creates a printer diagnostics adapter.
     *
     * @param vendorApi vendor API used to retrieve printer information
     */
    public PrinterDiagnosticsAdapter(PrinterVendorAPI vendorApi) {
        this.vendorApi = vendorApi;
    }

    /**
     * Converts vendor health data into the application's status format.
     *
     * @return equipment health status
     */
    @Override
    public String getStatus() {
        int health = vendorApi.getPrinterHealth();

        if (health >= 80) {
            return "GOOD";
        } else if (health >= 50) {
            return "WARNING";
        } else {
            return "CRITICAL";
        }
    }

    /**
     * Converts vendor usage minutes into equipment wear hours.
     *
     * @return equipment wear hours
     */
    @Override
    public double getWearHours() {
        long usageMinutes = vendorApi.getUsageMinutes();
        return usageMinutes / 60.0;
    }
}