<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, java.util.Map" %>
<%@ page import="DTO.Equipment, DTO.EquipmentSession, DTO.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Equipment Sessions</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">

    <h1>Equipment Check-In / Check-Out</h1>

    <% if (request.getAttribute("message") != null) { %>
        <p class="message"><%= request.getAttribute("message") %></p>
    <% } %>
    <% if (request.getAttribute("error") != null) { %>
        <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>

    <h2>Check In</h2>
    <form action="session" method="post">
        <input type="hidden" name="action" value="checkin">
        <label for="equipmentId">Equipment:</label>
        <select id="equipmentId" name="equipmentId" required>
            <%
                List<Equipment> equipmentList = (List<Equipment>) request.getAttribute("equipmentList");
                if (equipmentList != null) {
                    for (Equipment eq : equipmentList) {
            %>
                <option value="<%= eq.getEquipmentId() %>">
                    #<%= eq.getEquipmentId() %> - <%= eq.getAssetTag() %> (<%= eq.getStatus() %>)
                </option>
            <%
                    }
                }
            %>
        </select>
        <button type="submit">Check In</button>
    </form>

    <hr>

    <h2>Active Sessions (Live Usage Report)</h2>

    <%
        List<EquipmentSession> activeSessions = (List<EquipmentSession>) request.getAttribute("activeSessions");
        Map<Integer, Equipment> equipmentMap = (Map<Integer, Equipment>) request.getAttribute("equipmentMap");
        Map<Integer, User> userMap = (Map<Integer, User>) request.getAttribute("userMap");
    %>

    <% if (activeSessions != null && !activeSessions.isEmpty()) { %>
        <table>
            <tr>
                <th>Session</th>
                <th>Equipment</th>
                <th>Status</th>
                <th>Checked In By</th>
                <th>Check-In Time</th>
                <th>Elapsed Time</th>
                <th>Check Out</th>
            </tr>
            <% for (EquipmentSession s : activeSessions) {
                Equipment eq = equipmentMap.get(s.getEquipmentId());
                User u = userMap.get(s.getUserId());
                long elapsedMs = System.currentTimeMillis() - s.getCheckInTime().getTime();
                double elapsedHours = elapsedMs / 3600000.0;
            %>
                <tr>
                    <td><%= s.getSessionId() %></td>
                    <td><%= eq != null ? eq.getAssetTag() : "Equipment #" + s.getEquipmentId() %></td>
                    <td><%= eq != null ? eq.getStatus() : "Unknown" %></td>
                    <td><%= u != null ? u.getFirstName() + " " + u.getLastName() : "User #" + s.getUserId() %></td>
                    <td><%= s.getCheckInTime() %></td>
                    <td><%= String.format("%.2f", elapsedHours) %> hrs</td>
                    <td>
                        <form action="session" method="post">
                            <input type="hidden" name="action" value="checkout">
                            <input type="hidden" name="sessionId" value="<%= s.getSessionId() %>">
                            <input type="number" name="materialQuantity" step="0.01" min="0"
                                   placeholder="Materials used" value="0">
                            <button type="submit">Check Out</button>
                        </form>
                    </td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>No active sessions.</p>
    <% } %>

    <br>
    <p><a href="dashboard.jsp">Back to Dashboard</a></p>
</div>
</body>
</html>