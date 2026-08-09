package Builder;
import java.util.List;

/*
 * User Responsible: Nissen Sandinu Wettasinghe
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * Builder interface used to construct WorkOrder objects.
 */

/**
 * Defines the steps required to build a WorkOrder.
 *
 * @author nissenwettasinghe
 * @version 1.0
 */
public interface WorkOrderBuilder {

    /**
     * Sets the person requesting the work order.
     *
     * @param requestorId request user ID
     * @return builder instance
     */
    WorkOrderBuilder setRequestor(int requestorId);

    /**
     * Sets the work order description.
     *
     * @param description work description
     * @return builder instance
     */
    WorkOrderBuilder setDescription(String description);

    /**
     * Sets the work order priority.
     *
     * @param priority priority level
     * @return builder instance
     */
    WorkOrderBuilder setPriority(String priority);

    /**
     * Sets required materials.
     *
     * @param materials list of required materials
     * @return builder instance
     */
    WorkOrderBuilder setMaterials(List<String> materials);

    /**
     * Sets external client information.
     *
     * @param externalClientName external client's name
     * @return builder instance
     */
    WorkOrderBuilder setExternalClient(String externalClientName);

    /**
     * Builds and returns the completed WorkOrder.
     *
     * @return constructed WorkOrder
     */
    WorkOrder build();
}
