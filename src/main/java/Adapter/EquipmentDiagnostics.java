/*
 * User Responsible: Hasbiya Yusuf
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This interface defines the application diagnostics contract used by the
 * Adapter pattern. It provides a common interface for accessing equipment
 * health information without depending on vendor-specific implementations.
 */

package Adapter;

/**
 * Defines equipment diagnostic operations.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public interface EquipmentDiagnostics {

    /**
     * Returns the equipment health status.
     *
     * @return current equipment status
     */
    String getStatus();

    /**
     * Returns accumulated equipment wear time.
     *
     * @return wear hours
     */
    double getWearHours();
}