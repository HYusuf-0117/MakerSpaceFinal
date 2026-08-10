package Servlets;

import DAO.LedgerEntryDAO;
import DAO.LedgerEntryDAOImpl;
import DAO.UserDAOImpl;
import DTO.LedgerEntry;
import DTO.User;
import business.CreditService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import strategy.Activity;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Handles ledger display and credit activity requests.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
@WebServlet(name = "LedgerServlet", urlPatterns = {"/ledger"})
public class LedgerServlet extends HttpServlet {

    /**
     * Displays the current user's ledger.
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
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            User user = (User) session.getAttribute("user");
            LedgerEntryDAO ledgerDAO = new LedgerEntryDAOImpl();
            List<LedgerEntry> entries = ledgerDAO.findByUser(user.getUserId());

            request.setAttribute("ledgerEntries", entries);
            request.setAttribute("user", user);
            request.getRequestDispatcher("ledger.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Database error while loading ledger.", e);
        }
    }

    /**
     * Processes a credit-earning activity.
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
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            User user = (User) session.getAttribute("user");

            String activityType = request.getParameter("activityType");
            double hours = Double.parseDouble(request.getParameter("hours"));
            double donationValue = Double.parseDouble(request.getParameter("donationValue"));
            int workOrdersCompleted = Integer.parseInt(request.getParameter("workOrdersCompleted"));
            int trainingHours = Integer.parseInt(request.getParameter("trainingHours"));
            int maintenanceHours = Integer.parseInt(request.getParameter("maintenanceHours"));

            Activity activity = new Activity(
                    activityType,
                    hours,
                    donationValue,
                    workOrdersCompleted,
                    trainingHours,
                    maintenanceHours
            );

            activity.setUserId(user.getUserId());
            activity.setActivityDate(new Timestamp(System.currentTimeMillis()));

            CreditService creditService = new CreditService();
            double credits = creditService.processCredits(user.getUserId(), activity);

            request.setAttribute(
                    "message",
                    "Activity recorded successfully. " + credits + " credits earned."
            );

            User updatedUser = new UserDAOImpl().findById(user.getUserId());

            session.setAttribute("user", updatedUser);
            session.setAttribute("userId", updatedUser.getUserId());
            session.setAttribute("userType", updatedUser.getUserType());
            session.setAttribute(
                    "userName",
                    updatedUser.getFirstName() + " " + updatedUser.getLastName()
            );

            doGet(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Please enter valid numeric values.");
            doGet(request, response);

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            doGet(request, response);

        } catch (SQLException e) {
            throw new ServletException(
                    "Database error while processing credit activity.",
                    e
            );
        }
    }
}