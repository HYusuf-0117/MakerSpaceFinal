package Servlets;

import DAO.EquipmentDAO;
import DAO.EquipmentDAOImpl;
import DAO.MaintenanceAlertDAO;
import DAO.MaintenanceAlertDAOImpl;
import DTO.Equipment;
import Observer.EquipmentSubject;
import Observer.MaintenanceAlertLogger;
import Observer.ShopTechNotifier;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Handles equipment maintenance monitoring and usage updates.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
@WebServlet(name = "MaintenanceServlet", urlPatterns = {"/maintenance"})
public class MaintenanceServlet extends HttpServlet {

    /**
     * Displays the maintenance page with all equipment.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            EquipmentDAO equipmentDAO = new EquipmentDAOImpl();

            request.setAttribute("equipmentList", equipmentDAO.findAll());

            request.getRequestDispatcher("maintenance.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            request.setAttribute(
                    "error",
                    "Unable to load equipment: " + e.getMessage()
            );

            request.getRequestDispatcher("maintenance.jsp")
                    .forward(request, response);
        }
    }

    /**
     * Processes equipment usage and triggers maintenance observers
     * when the maintenance threshold is reached.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EquipmentDAO equipmentDAO = null;

        try {
            int equipmentId = Integer.parseInt(
                    request.getParameter("equipmentId")
            );

            double hoursUsed = Double.parseDouble(
                    request.getParameter("hoursUsed")
            );

            if (hoursUsed <= 0) {
                throw new IllegalArgumentException(
                        "Usage hours must be greater than zero."
                );
            }

            equipmentDAO = new EquipmentDAOImpl();

            Equipment equipment = equipmentDAO.findById(equipmentId);

            if (equipment == null) {
                throw new IllegalArgumentException(
                        "Equipment not found."
                );
            }

            MaintenanceAlertDAO alertDAO =
                    new MaintenanceAlertDAOImpl();

            EquipmentSubject subject =
                    new EquipmentSubject(equipment, equipmentDAO);

            // Register Observer responsible for saving alerts
            subject.addObserver(
                    new MaintenanceAlertLogger(alertDAO)
            );

            // Register Observer responsible for notifying Shop-Tech users
            subject.addObserver(
                    new ShopTechNotifier()
            );

            // Add usage and check maintenance threshold
            subject.addUsage(hoursUsed);

            request.setAttribute(
                    "message",
                    "Equipment usage updated successfully."
            );

            request.setAttribute(
                    "equipment",
                    equipment
            );

            request.setAttribute(
                    "hoursUsed",
                    hoursUsed
            );

            request.setAttribute(
                    "maintenanceRequired",
                    "Maintenance".equals(equipment.getStatus())
            );

            // Reload equipment after update
            request.setAttribute(
                    "equipmentList",
                    equipmentDAO.findAll()
            );

            request.getRequestDispatcher("maintenance.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Equipment ID and usage hours must be valid numbers."
            );

            reloadEquipment(request, equipmentDAO);

            request.getRequestDispatcher("maintenance.jsp")
                    .forward(request, response);

        } catch (IllegalArgumentException e) {

            request.setAttribute(
                    "error",
                    e.getMessage()
            );

            reloadEquipment(request, equipmentDAO);

            request.getRequestDispatcher("maintenance.jsp")
                    .forward(request, response);

        } catch (SQLException e) {

            request.setAttribute(
                    "error",
                    "Database error: " + e.getMessage()
            );

            reloadEquipment(request, equipmentDAO);

            request.getRequestDispatcher("maintenance.jsp")
                    .forward(request, response);
        }
    }

    /**
     * Reloads the equipment list so that the maintenance page
     * continues displaying equipment after an error.
     *
     * @param request servlet request
     * @param equipmentDAO existing equipment DAO, if available
     */
    private void reloadEquipment(
            HttpServletRequest request,
            EquipmentDAO equipmentDAO) {

        try {
            if (equipmentDAO == null) {
                equipmentDAO = new EquipmentDAOImpl();
            }

            request.setAttribute(
                    "equipmentList",
                    equipmentDAO.findAll()
            );

        } catch (SQLException e) {
            request.setAttribute(
                    "error",
                    "Unable to reload equipment: " + e.getMessage()
            );
        }
    }
}