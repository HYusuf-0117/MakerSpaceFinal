<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CMSC Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

    <div class="container">

        <h1>Campus Maker Space Co-op</h1>
        <h2>Login</h2>

        <% if (request.getAttribute("error") != null) { %>
            <p class="error">
                <%= request.getAttribute("error") %>
            </p>
        <% } %>

        <form action="login" method="post">

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

            <button type="submit">
                Login
            </button>

        </form>

        <br>

        <p>
            Don't have an account?
            <a href="registerUser.jsp">
                Create Account
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