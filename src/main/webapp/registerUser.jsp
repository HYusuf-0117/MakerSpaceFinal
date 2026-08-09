<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Account</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

    <div class="container">

        <h1>Campus Maker Space Co-op</h1>
        <h2>Create Account</h2>

        <% if (request.getAttribute("message") != null) { %>
            <p class="message">
                <%= request.getAttribute("message") %>
            </p>
        <% } %>

        <% if (request.getAttribute("error") != null) { %>
            <p class="error">
                <%= request.getAttribute("error") %>
            </p>
        <% } %>

        <form action="register" method="post">

            <label for="firstName">First Name:</label>

            <input type="text"
                   id="firstName"
                   name="firstName"
                   required>

            <label for="lastName">Last Name:</label>

            <input type="text"
                   id="lastName"
                   name="lastName"
                   required>

            <label for="email">Email:</label>

            <input type="email"
                   id="email"
                   name="email"
                   required>

            <label for="password">Password:</label>

            <input type="password"
                   id="password"
                   name="password"
                   required>

            <label for="userType">User Type:</label>

            <select id="userType"
                    name="userType"
                    required>

                <option value="">Select User Type</option>
                <option value="Member">Member</option>
                <option value="Trainer">Trainer</option>
                <option value="Shop-Tech">Shop-Tech</option>

            </select>

            <button type="submit">
                Create Account
            </button>

        </form>

        <br>

        <p>
            Already have an account?
            <a href="login.jsp">
                Login
            </a>
        </p>

        <p>
            <a href="index.html">
                Back to Home
            </a>
        </p>

    </div>

</body>
</html>