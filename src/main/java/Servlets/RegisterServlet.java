package Servlets;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import DTO.User;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Handles user registration requests.
 *
 * @author Chance Boukoro Bakala
 * @version 1.0
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    /**
     * Displays the registration page.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("registerUser.jsp")
                .forward(request, response);
    }

    /**
     * Processes user registration.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        try {
            UserDAO userDAO = new UserDAOImpl();

            User existingUser = userDAO.findByEmail(email);

            if (existingUser != null) {
                request.setAttribute(
                        "error",
                        "An account with that email already exists."
                );

                request.getRequestDispatcher("registerUser.jsp")
                        .forward(request, response);

                return;
            }

            String passwordHash =
                    BCrypt.hashpw(password, BCrypt.gensalt());

            User user = new User(
                    0,
                    firstName,
                    lastName,
                    email,
                    passwordHash,
                    userType,
                    0.0,
                    new Timestamp(System.currentTimeMillis())
            );

            userDAO.create(user);

            request.setAttribute(
                    "message",
                    "Account created successfully. You can now login."
            );

            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(
                    "Database error while creating account.",
                    e
            );
        }
    }
}