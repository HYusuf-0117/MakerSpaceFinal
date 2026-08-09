package Servlets;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import DTO.User;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Handles user login requests.
 *
 * @author Chance Boukoro Bakala
 * @version 1.0
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    /**
     * Displays the login page.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("login.jsp")
                .forward(request, response);
    }

    /**
     * Processes login requests.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            UserDAO userDAO = new UserDAOImpl();
            User user = userDAO.findByEmail(email);

            if (user != null
                    && BCrypt.checkpw(password, user.getPasswordHash())) {

                HttpSession session = request.getSession();

                session.setAttribute("user", user);
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("userType", user.getUserType());
                session.setAttribute("userName",
                        user.getFirstName() + " " + user.getLastName());

                response.sendRedirect("dashboard.jsp");

            } else {

                request.setAttribute(
                        "error",
                        "Invalid email or password."
                );

                request.getRequestDispatcher("login.jsp")
                        .forward(request, response);
            }

        } catch (SQLException e) {

            throw new ServletException(
                    "Database error while attempting to login.",
                    e
            );
        }
    }
}