package Servlets;

import DAO.EquipmentDAO;
import DAO.EquipmentDAOImpl;
import DTO.Equipment;

import java.sql.SQLException;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.sql.SQLException;
import DAO.EquipmentDAO;
import DAO.EquipmentDAOImpl;
import DTO.Equipment;
import SimpleFactory.EquipmentFactory;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Handles equipment registration and equipment list requests.
 *
 * @author Chance Boukoro Bakala
 * @version 1.0
 */
@WebServlet(name = "EquipmentServlet", urlPatterns = {"/equipment"})
public class EquipmentServlet extends HttpServlet {

    /**
     * Displays the equipment list page.
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

            List<Equipment> equipmentList = equipmentDAO.findAll();

            request.setAttribute("equipmentList", equipmentList);

            request.getRequestDispatcher("equipmentList.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            request.setAttribute(
                    "error",
                    "Unable to load equipment: " + e.getMessage()
            );

            request.getRequestDispatcher("equipmentList.jsp")
                    .forward(request, response);
        }
    }

    /**
     * Processes equipment registration.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || !"Shop-Tech".equals(session.getAttribute("userType"))) {
            response.sendError( 
                    HttpServletResponse.SC_FORBIDDEN, "Only Shop-Tech users can register equipment."
            );
            return;
        }
        
        try {
            String type = request.getParameter("type");
            String assetTag = request.getParameter("assetTag");
            String make = request.getParameter("make");
            String model = request.getParameter("model");
            
            String consumableType = request.getParameter("consumableType");

            double hourlyRate =
                    Double.parseDouble(
                            request.getParameter("hourlyRate")
            );

            double maintenanceThreshold =
                    Double.parseDouble(
                            request.getParameter("maintenanceThreshold")
            );

            Equipment equipment = EquipmentFactory.createEquipment( 
                type, 0, assetTag, make, model,
                "Available", hourlyRate,0.0,
                maintenanceThreshold
            );
            
            equipment.setConsumableType(consumableType);

            EquipmentDAO equipmentDAO = new EquipmentDAOImpl();
            equipmentDAO.create(equipment);

            request.setAttribute(
                    "equipment",
                    equipment
            );

            request.setAttribute(
                    "message",
                    "Equipment created successfully."
            );

            request.getRequestDispatcher("registerEquipment.jsp")
                    .forward(request, response);

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Hourly rate and maintenance threshold must be valid numbers."
            );

            request.getRequestDispatcher("registerEquipment.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            request.setAttribute(
                    "error",
                    "Database error: " + e.getMessage()
            );

            request.getRequestDispatcher("registerEquipment.jsp")
                    .forward(request, response);
        }
    }
}