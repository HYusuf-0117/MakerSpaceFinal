package Servlets;

import DAO.EquipmentDAO;
import DAO.EquipmentDAOImpl;
import DAO.EquipmentSessionDAO;
import DAO.EquipmentSessionDAOImpl;
import DAO.UserDAO;
import DAO.UserDAOImpl;
import DTO.Equipment;
import DTO.EquipmentSession;
import DTO.User;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Handles equipment check-in / check-out and the live usage report.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
@WebServlet(name = "EquipmentSessionServlet", urlPatterns = {"/session"})
public class EquipmentSessionServlet extends HttpServlet {

    /**
     * Displays active equipment sessions and equipment information.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            EquipmentSessionDAO sessionDAO =
                    new EquipmentSessionDAOImpl();
            EquipmentDAO equipmentDAO =
                    new EquipmentDAOImpl();
            UserDAO userDAO =
                    new UserDAOImpl();

            List<EquipmentSession> activeSessions =
                    sessionDAO.findAll();

            activeSessions.removeIf(
                    s -> s.getCheckOutTime() != null
            );

            Map<Integer, Equipment> equipmentMap =
                    new HashMap<>();

            for (Equipment e : equipmentDAO.findAll()) {
                equipmentMap.put(
                        e.getEquipmentId(),
                        e
                );
            }

            Map<Integer, User> userMap =
                    new HashMap<>();

            for (EquipmentSession s : activeSessions) {
                if (!userMap.containsKey(s.getUserId())) {
                    User u = userDAO.findById(s.getUserId());

                    if (u != null) {
                        userMap.put(
                                s.getUserId(),
                                u
                        );
                    }
                }
            }

            request.setAttribute(
                    "activeSessions",
                    activeSessions
            );

            request.setAttribute(
                    "equipmentMap",
                    equipmentMap
            );

            request.setAttribute(
                    "userMap",
                    userMap
            );

            request.setAttribute(
                    "equipmentList",
                    equipmentDAO.findAll()
            );

            request.getRequestDispatcher(
                    "session.jsp"
            ).forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(
                    "Database error while loading sessions.",
                    e
            );
        }
    }

    /**
     * Processes equipment check-in and check-out requests.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession =
                request.getSession(false);

        if (httpSession == null
                || httpSession.getAttribute("user") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        User currentUser =
                (User) httpSession.getAttribute("user");

        String action =
                request.getParameter("action");

        try {
            EquipmentDAO equipmentDAO =
                    new EquipmentDAOImpl();
            EquipmentSessionDAO sessionDAO =
                    new EquipmentSessionDAOImpl();

            if ("checkin".equals(action)) {

                int equipmentId =
                        Integer.parseInt(
                                request.getParameter("equipmentId")
                        );

                Equipment equipment =
                        equipmentDAO.findById(equipmentId);

                if (equipment == null) {
                    throw new IllegalArgumentException(
                            "Equipment not found."
                    );
                }

                if (!"Available".equals(
                        equipment.getStatus())) {

                    throw new IllegalArgumentException(
                            "Equipment is not available "
                            + "(current status: "
                            + equipment.getStatus()
                            + ")."
                    );
                }

                EquipmentSession newSession =
                        new EquipmentSession();

                newSession.setUserId(
                        currentUser.getUserId()
                );

                newSession.setEquipmentId(
                        equipmentId
                );

                newSession.setCheckInTime(
                        new Timestamp(
                                System.currentTimeMillis()
                        )
                );

                newSession.setMaterialQuantity(0.0);
                newSession.setTotalDebit(0.0);

                sessionDAO.create(newSession);

                equipmentDAO.updateStatus(
                        equipmentId,
                        "In-Use"
                );

                request.setAttribute(
                        "message",
                        "Checked in to equipment #"
                        + equipmentId
                        + "."
                );

            } else if ("checkout".equals(action)) {

                int sessionId =
                        Integer.parseInt(
                                request.getParameter("sessionId")
                        );

                double materialQuantity =
                        parseOrZero(
                                request.getParameter(
                                        "materialQuantity"
                                )
                        );

                EquipmentSession activeSession =
                        sessionDAO.findById(sessionId);

                if (activeSession == null
                        || activeSession.getCheckOutTime() != null) {

                    throw new IllegalArgumentException(
                            "Active session not found."
                    );
                }

                Timestamp checkOutTime =
                        new Timestamp(
                                System.currentTimeMillis()
                        );

                double elapsedHours =
                        (checkOutTime.getTime()
                        - activeSession.getCheckInTime().getTime())
                        / 3_600_000.0;

                // Flat demo debit: 1 credit/hour + material quantity.
                double totalDebit =
                        elapsedHours + materialQuantity;

                sessionDAO.checkOut(
                        sessionId,
                        checkOutTime,
                        materialQuantity,
                        totalDebit
                );

                equipmentDAO.updateStatus(
                        activeSession.getEquipmentId(),
                        "Available"
                );

                request.setAttribute(
                        "message",
                        "Checked out. Elapsed time: "
                        + String.format(
                                "%.2f",
                                elapsedHours
                        )
                        + " hrs. Debited: "
                        + String.format(
                                "%.2f",
                                totalDebit
                        )
                        + " credits."
                );
            }

            doGet(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute(
                    "error",
                    "Please enter valid numeric values."
            );

            doGet(request, response);

        } catch (IllegalArgumentException e) {
            request.setAttribute(
                    "error",
                    e.getMessage()
            );

            doGet(request, response);

        } catch (SQLException e) {
            throw new ServletException(
                    "Database error while processing check-in/out.",
                    e
            );
        }
    }

    /**
     * Converts a blank value to zero.
     *
     * @param value value to convert
     * @return parsed value or zero
     */
    private double parseOrZero(String value) {
        if (value == null || value.isBlank()) {
            return 0.0;
        }

        return Double.parseDouble(value);
    }
}