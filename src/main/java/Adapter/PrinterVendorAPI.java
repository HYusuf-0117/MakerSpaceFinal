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

    /**
     * Returns the vendor-reported printer health score.
     *
     * @return health score from 0 to 100
     */
    public int getPrinterHealth() {
        return 100;
    }

    /**
     * Returns the vendor-reported printer usage time.
     *
     * @return cumulative usage time in minutes
     */
    public long getUsageMinutes() {
        return 0L;
    }
}