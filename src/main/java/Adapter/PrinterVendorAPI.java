/*
 * User Responsible: Hasbiya Yusuf
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This class represents a third-party printer vendor API used by the
 * Adapter pattern. It provides vendor-specific equipment information that
 * is converted by PrinterDiagnosticsAdapter into the application's format.
 */

package Adapter;

/**
 * Represents the external printer vendor API.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public class PrinterVendorAPI {

    private final int printerHealth;
    private final long usageMinutes;

    /**
     * @param printerHealth vendor-reported health score, 0-100
     * @param usageMinutes  vendor-reported cumulative usage time, in minutes
     */
    public PrinterVendorAPI(int printerHealth, long usageMinutes) {
        this.printerHealth = printerHealth;
        this.usageMinutes = usageMinutes;
    }

    /**
     * Returns the vendor-reported printer health score.
     *
     * @return health score from 0 to 100
     */
    public int getPrinterHealth() {
        return printerHealth;
    }

    /**
     * Returns the vendor-reported printer usage time.
     *
     * @return cumulative usage time in minutes
     */
    public long getUsageMinutes() {
        return usageMinutes;
    }
}