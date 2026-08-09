package Observer;

/**
 * Observer Interface for Maintenance alerts.
 * @author Auston Gurr
 */
public interface MaintenanceObserver {
    void onMaintenanceThresholdCrossed(MaintenanceAlert alert);
}
