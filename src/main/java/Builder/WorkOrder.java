package Builder;

import java.util.ArrayList;
import java.util.List;

/*
 * User Responsible: Nissen Sandinu Wettasinghe
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * Represents a work order in the Campus Maker Space Co-op.
 * This object is created using the Builder design pattern.
 */

/**
 * Represents a fabrication work order in the Maker Space system.
 *
 * @author nissenwettasinghe
 * @version 1.0
 */
public class WorkOrder {

    private int workOrderId;
    private int requestorId;
    private String description;
    private String priority;
    private List<String> materials;
    private String externalClientName;
    private String status;

    /**
     * Creates an empty WorkOrder.
     */
    public WorkOrder() {
        this.materials = new ArrayList<>();
        this.status = "PENDING";
    }

    /**
     * Returns the work order ID.
     *
     * @return work order ID
     */
    public int getWorkOrderId() {
        return workOrderId;
    }

    /**
     * Sets the work order ID.
     *
     * @param workOrderId work order ID
     */
    public void setWorkOrderId(int workOrderId) {
        this.workOrderId = workOrderId;
    }

    /**
     * Returns the ID of the user requesting the work.
     *
     * @return requestor ID
     */
    public int getRequestorId() {
        return requestorId;
    }

    /**
     * Sets the ID of the user requesting the work.
     *
     * @param requestorId requestor ID
     */
    public void setRequestorId(int requestorId) {
        this.requestorId = requestorId;
    }

    /**
     * Returns the work order description.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the work order description.
     *
     * @param description description of requested work
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the priority.
     *
     * @return priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Sets the priority.
     *
     * @param priority work order priority
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * Returns the required materials.
     *
     * @return list of materials
     */
    public List<String> getMaterials() {
        return materials;
    }

    /**
     * Sets the required materials.
     *
     * @param materials list of materials
     */
    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    /**
     * Returns the external client name.
     *
     * @return external client name
     */
    public String getExternalClientName() {
        return externalClientName;
    }

    /**
     * Sets the external client name.
     *
     * @param externalClientName external client name
     */
    public void setExternalClientName(String externalClientName) {
        this.externalClientName = externalClientName;
    }

    /**
     * Returns the work order status.
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the work order status.
     *
     * @param status work order status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WorkOrder{"
                + "workOrderId=" + workOrderId
                + ", requestorId=" + requestorId
                + ", description='" + description + '\''
                + ", priority='" + priority + '\''
                + ", materials=" + materials
                + ", externalClientName='" + externalClientName + '\''
                + ", status='" + status + '\''
                + '}';
    }
}
