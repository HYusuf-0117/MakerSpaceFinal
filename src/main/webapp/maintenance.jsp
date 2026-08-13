<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Equipment Maintenance</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>Equipment Maintenance Test</h1>
<p>Use this page to add equipment usage and test the maintenance Observer.</p>
<% if (request.getAttribute("message") != null) { %>
    <p style="color: green;">
        <%= request.getAttribute("message") %>
    </p>
<% } %>
<% if (request.getAttribute("error") != null) { %>
    <p style="color: red;">
        <%= request.getAttribute("error") %>
    </p>
<% } %>
<form action="maintenance" method="post">
    <label for="equipmentId">Equipment ID:</label>
    <input type="number"
           id="equipmentId"
           name="equipmentId"
           min="1"
           required>
    <br><br>
    <label for="hoursUsed">Usage Hours:</label>
    <input type="number"
           id="hoursUsed"
           name="hoursUsed"
           min="0.01"
           step="0.01"
           required>
    <br><br>
    <button type="submit">Add Usage</button>
</form>

<hr>

<h2>Check Vendor Diagnostics (3D Printers only)</h2>

<p>Simulates a reading from the printer vendor's SDK, translated through the Adapter.</p>

<form action="maintenance" method="post">

    <input type="hidden" name="action" value="vendorCheck">

    <label for="vendorEquipmentId">Equipment ID:</label>
    <input type="number"
           id="vendorEquipmentId"
           name="equipmentId"
           min="1"
           required>

    <br><br>

    <label for="simulatedHealth">Simulated Vendor Health Score (0-100):</label>
    <input type="number"
           id="simulatedHealth"
           name="simulatedHealth"
           min="0"
           max="100"
           required>

    <br><br>

    <label for="simulatedMinutes">Simulated Vendor Usage Minutes:</label>
    <input type="number"
           id="simulatedMinutes"
           name="simulatedMinutes"
           min="0"
           required>

    <br><br>

    <button type="submit">Check Diagnostics</button>

</form>

<% if (request.getAttribute("equipment") != null) { %>
    <hr>
    <h2>Equipment Updated</h2>
    <p>
        Equipment ID:
        <%= ((DTO.Equipment) request.getAttribute("equipment")).getEquipmentId() %>
    </p>
    <p>
        Asset Tag:
        <%= ((DTO.Equipment) request.getAttribute("equipment")).getAssetTag() %>
    </p>
    <p>
        Usage Hours:
        <%= ((DTO.Equipment) request.getAttribute("equipment")).getUsageHours() %>
    </p>
    <p>
        Maintenance Threshold:
        <%= ((DTO.Equipment) request.getAttribute("equipment")).getMaintenanceThreshold() %>
    </p>
    <p>
        Status:
        <%= ((DTO.Equipment) request.getAttribute("equipment")).getStatus() %>
    </p>
    <% if (Boolean.TRUE.equals(request.getAttribute("maintenanceRequired"))) { %>
        <h3 style="color: red;">
            MAINTENANCE REQUIRED
        </h3>
    <% } %>
<% } %>
<hr>
<h2>Available Equipment</h2>
<%
    java.util.List<DTO.Equipment> equipmentList =
            (java.util.List<DTO.Equipment>) request.getAttribute("equipmentList");
    if (equipmentList != null && !equipmentList.isEmpty()) {
%>
<table border="1" cellpadding="8">
    <tr>
        <th>ID</th>
        <th>Asset Tag</th>
        <th>Make</th>
        <th>Model</th>
        <th>Status</th>
        <th>Usage Hours</th>
        <th>Maintenance Threshold</th>
    </tr>
    <% for (DTO.Equipment equipment : equipmentList) { %>
    <tr>
        <td><%= equipment.getEquipmentId() %></td>
        <td><%= equipment.getAssetTag() %></td>
        <td><%= equipment.getMake() %></td>
        <td><%= equipment.getModel() %></td>
        <td><%= equipment.getStatus() %></td>
        <td><%= equipment.getUsageHours() %></td>
        <td><%= equipment.getMaintenanceThreshold() %></td>
    </tr>
    <% } %>
</table>
<%
    } else {
%>
<p>No equipment found.</p>
<%
    }
%>
</body>
</html>