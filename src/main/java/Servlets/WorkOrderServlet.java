package Servlets;

import Builder.ConcreteWorkOrderBuilder;
import Builder.WorkOrder;
import Builder.WorkOrderBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Handles work order creation requests.
 *
 * @author Chance Boukoro Bakala
 * @version 1.0
 */
@WebServlet(name = "WorkOrderServlet", urlPatterns = {"/workorder"})
public class WorkOrderServlet extends HttpServlet {

    /**
     * Displays the work order page.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("workOrder.jsp")
                .forward(request, response);
    }

    /**
     * Processes work order creation.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int requestorId =
                    Integer.parseInt(request.getParameter("requestorId"));

            String description =
                    request.getParameter("description");

            String priority =
                    request.getParameter("priority");

            String materialsText =
                    request.getParameter("materials");

            String externalClientName =
                    request.getParameter("externalClientName");

            List<String> materials = new ArrayList<>();

            if (materialsText != null && !materialsText.isBlank()) {
                materials = Arrays.stream(materialsText.split(","))
                        .map(String::trim)
                        .filter(item -> !item.isBlank())
                        .toList();
            }

            WorkOrderBuilder builder =
                    new ConcreteWorkOrderBuilder();

            WorkOrder workOrder = builder
                    .setRequestor(requestorId)
                    .setDescription(description)
                    .setPriority(priority)
                    .setMaterials(materials)
                    .setExternalClient(externalClientName)
                    .build();

            request.setAttribute("workOrder", workOrder);

            request.setAttribute(
                    "message",
                    "Work order created successfully."
            );

            request.getRequestDispatcher("workOrder.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Requestor ID must be a valid number."
            );

            request.getRequestDispatcher("workOrder.jsp")
                    .forward(request, response);

        } catch (IllegalStateException e) {

            request.setAttribute(
                    "error",
                    e.getMessage()
            );

            request.getRequestDispatcher("workOrder.jsp")
                    .forward(request, response);
        }
    }
}