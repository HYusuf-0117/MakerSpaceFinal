package business;

import Adapter.EquipmentDiagnostics;
import DAO.EquipmentDAO;
import DAO.MaintenanceAlertDAO;
import DTO.Equipment;
import Observer.EquipmentSubject;
import Observer.MaintenanceAlertLogger;
import Observer.ShopTechNotifier;

import java.sql.SQLException;

/**
 * Business-layer service that consumes vendor equipment diagnostics
 * (translated into the application's EquipmentDiagnostics contract by an
 * Adapter) and feeds them into the existing usage/threshold/Observer
 * maintenance pipeline. Implements FR-05's diagnostics monitoring.
 */
public class MaintanenceMonitorService {

    private final EquipmentDAO equipmentDAO;
    private final MaintenanceAlertDAO alertDAO;

    public MaintanenceMonitorService(EquipmentDAO equipmentDAO, MaintenanceAlertDAO alertDAO) {
        this.equipmentDAO = equipmentDAO;
        this.alertDAO = alertDAO;
    }

    /**
     * Polls a piece of equipment's diagnostics (via its vendor Adapter) and
     * applies the result: any new wear hours are recorded through the
     * normal threshold check, and a CRITICAL health reading forces
     * maintenance immediately, regardless of accumulated hours.
     *
     * @param equipment   the equipment being checked
     * @param diagnostics vendor diagnostics, already translated by an Adapter
     * @throws SQLException if a database update fails
     */
    public void checkEquipment(Equipment equipment, EquipmentDiagnostics diagnostics) throws SQLException {

        EquipmentSubject subject = new EquipmentSubject(equipment, equipmentDAO);
        subject.addObserver(new MaintenanceAlertLogger(alertDAO));
        subject.addObserver(new ShopTechNotifier());

        // Vendor wear hours are cumulative; only add what we haven't recorded yet.
        double newHours = diagnostics.getWearHours() - equipment.getUsageHours();
        if (newHours > 0) {
            subject.addUsage(newHours);
        }

        // A critical health reading overrides the hours-based threshold entirely.
        if ("CRITICAL".equals(diagnostics.getStatus())) {
            subject.forceMaintenance();
        }
    }
}