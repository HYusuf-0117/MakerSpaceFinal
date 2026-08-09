package Builder;

import java.util.List;

/*
 * User Responsible: Nissen Sandinu Wettasinghe
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * Director class for the Builder pattern.
 * Provides common construction sequences for WorkOrder objects.
 */

/**
 * Directs the construction of common WorkOrder configurations.
 *
 * @author nissenwettasinghe
 * @version 1.0
 */
public class WorkOrderDirector {

    /**
     * Creates a standard internal work order.
     *
     * @param builder builder used to create the work order
     * @param requestorId member requesting the job
     * @param description description of the fabrication job
     * @param materials required materials
     * @return constructed WorkOrder
     */
    public WorkOrder constructStandardOrder(
            WorkOrderBuilder builder,
            int requestorId,
            String description,
            List<String> materials) {

        return builder
                .setRequestor(requestorId)
                .setDescription(description)
                .setPriority("NORMAL")
                .setMaterials(materials)
                .build();
    }

    /**
     * Creates an external-client work order.
     *
     * @param builder builder used to create the work order
     * @param requestorId member or Shop-Tech submitting the job
     * @param description description of the fabrication job
     * @param materials required materials
     * @param externalClientName external client name
     * @return constructed WorkOrder
     */
    public WorkOrder constructExternalClientOrder(
            WorkOrderBuilder builder,
            int requestorId,
            String description,
            List<String> materials,
            String externalClientName) {

        return builder
                .setRequestor(requestorId)
                .setDescription(description)
                .setPriority("HIGH")
                .setMaterials(materials)
                .setExternalClient(externalClientName)
                .build();
    }
}
