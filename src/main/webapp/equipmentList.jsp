<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DTO.Equipment" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Equipment List</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

    <div class="container">

        <h1>Equipment List</h1>

        <% if (request.getAttribute("error") != null) { %>
            <p class="error">
                <%= request.getAttribute("error") %>
            </p>
        <% } %>

        <%
            List<Equipment> equipmentList =
                    (List<Equipment>) request.getAttribute("equipmentList");
        %>

        <% if (equipmentList != null && !equipmentList.isEmpty()) { %>

            <table>

                <tr>
                    <th>ID</th>
                    <th>Asset Tag</th>
                    <th>Make</th>
                    <th>Model</th>
                    <th>Category</th>
                    <th>Status</th>
                    <th>Hourly Rate</th>
                    <th>Usage Hours</th>
                    <th>Maintenance Threshold</th>
                </tr>

                <% for (Equipment equipment : equipmentList) { %>

                    <tr>
                        <td><%= equipment.getEquipmentId() %></td>
                        <td><%= equipment.getAssetTag() %></td>
                        <td><%= equipment.getMake() %></td>
                        <td><%= equipment.getModel() %></td>
                        <td><%= equipment.getCategory() %></td>
                        <td><%= equipment.getStatus() %></td>
                        <td><%= equipment.getHourlyRate() %></td>
                        <td><%= equipment.getUsageHours() %></td>
                        <td><%= equipment.getMaintenanceThreshold() %></td>
                    </tr>

                <% } %>

            </table>

        <% } else { %>

            <p>No equipment found.</p>

        <% } %>

        <br>

        <p>
            <a href="registerEquipment.jsp">
                Register New Equipment
            </a>
        </p>

        <p>
            <a href="dashboard.jsp">
                Back to Dashboard
            </a>
        </p>

    </div>

</body>
</html>