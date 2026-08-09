<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CMSC Dashboard</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

    <%
        String userName = (String) session.getAttribute("userName");
        String userType = (String) session.getAttribute("userType");

        if (userName == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>

    <div class="container">

        <h1>Campus Maker Space Co-op</h1>

        <h2>Welcome, <%= userName %></h2>

        <p>
            User Type: <strong><%= userType %></strong>
        </p>

        <hr>

        <h3>Dashboard</h3>

        <ul class="nav-list">

            <li>
                <a href="registerEquipment.jsp">
                    Register Equipment
                </a>
            </li>

            <li>
                <a href="equipment">
                    View Equipment
                </a>
            </li>

            <li>
                <a href="workOrder.jsp">
                    Create Work Order
                </a>
            </li>

            <li>
                <a href="logout">
                    Logout
                </a>
            </li>

        </ul>

    </div>

</body>
</html>