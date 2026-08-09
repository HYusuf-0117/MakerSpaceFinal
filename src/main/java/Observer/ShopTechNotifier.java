package Observer;

/**
 * Observer that notifies shop-tech users when maintenance is required.
 * @author Auston Gurr
 */
public class ShopTechNotifier implements MaintenanceObserver {
    
    @Override
    public void onMaintenanceThresholdCrossed(MaintenanceAlert alert) {
        System.out.println("Notify Shop-Tech: " + alert.getMessage());
    }
}
