package Builder;
import java.util.ArrayList;
import java.util.List;

/*
 * User Responsible: Nissen Sandinu Wettasinghe
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * Concrete implementation of the WorkOrderBuilder interface.
 */

/**
 * Concrete builder responsible for constructing WorkOrder objects.
 *
 * @author nissenwettasinghe
 * @version 1.0
 */
public class ConcreteWorkOrderBuilder implements WorkOrderBuilder {

    private final WorkOrder workOrder;

    /**
     * Creates a new builder with an empty WorkOrder.
     */
    public ConcreteWorkOrderBuilder() {
        workOrder = new WorkOrder();
    }

    /**
     * Sets the requester.
     *
     * @param requestorId requester ID
     * @return this builder
     */
    @Override
    public WorkOrderBuilder setRequestor(int requestorId) {
        workOrder.setRequestorId(requestorId);
        return this;
    }

    /**
     * Sets the description.
     *
     * @param description work description
     * @return this builder
     */
    @Override
    public WorkOrderBuilder setDescription(String description) {
        workOrder.setDescription(description);
        return this;
    }

    /**
     * Sets the priority.
     *
     * @param priority work order priority
     * @return this builder
     */
    @Override
    public WorkOrderBuilder setPriority(String priority) {
        workOrder.setPriority(priority);
        return this;
    }

    /**
     * Sets the material list.
     *
     * @param materials required materials
     * @return this builder
     */
    @Override
    public WorkOrderBuilder setMaterials(List<String> materials) {

        if (materials == null) {
            workOrder.setMaterials(new ArrayList<>());
        } else {
            workOrder.setMaterials(new ArrayList<>(materials));
        }

        return this;
    }

    /**
     * Sets external client information.
     *
     * @param externalClientName external client name
     * @return this builder
     */
    @Override
    public WorkOrderBuilder setExternalClient(String externalClientName) {
        workOrder.setExternalClientName(externalClientName);
        return this;
    }

    /**
     * Builds the WorkOrder.
     *
     * @return completed WorkOrder
     * @throws IllegalStateException when required values are missing
     */
    @Override
    public WorkOrder build() {

        if (workOrder.getRequestorId() <= 0) {
            throw new IllegalStateException(
                    "A requestor is required to create a work order.");
        }

        if (workOrder.getDescription() == null
                || workOrder.getDescription().isBlank()) {

            throw new IllegalStateException(
                    "A description is required to create a work order.");
        }

        if (workOrder.getPriority() == null
                || workOrder.getPriority().isBlank()) {
            workOrder.setPriority("NORMAL");
        }

        if (workOrder.getStatus() == null
                || workOrder.getStatus().isBlank()) {
            workOrder.setStatus("PENDING");
        }

        return workOrder;
    }
}