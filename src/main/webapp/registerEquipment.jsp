<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register Equipment</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

    <div class="container">

        <h1>Register Equipment</h1>

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

        <form action="equipment" method="post">

            <label for="type">Equipment Type:</label>

            <select id="type" name="type" required>
                <option value="">Select Equipment Type</option>
                <option value="3D Printer">3D Printer</option>
                <option value="Laser Cutter">Laser Cutter</option>
                <option value="CNC Machine">CNC Machine</option>
            </select>

            <label for="assetTag">Asset Tag:</label>

            <input type="text"
                   id="assetTag"
                   name="assetTag"
                   required>

            <label for="make">Make:</label>

            <input type="text"
                   id="make"
                   name="make"
                   required>

            <label for="model">Model:</label>

            <input type="text"
                   id="model"
                   name="model"
                   required>
            
            <label for="consumableType">Consumable Type:</label>

            <input type="text"
                   id="consumableType"
                   name="consumableType"
                   placeholder="e.g. PLA Filament">

            <label for="hourlyRate">Hourly Rate:</label>

            <input type="number"
                   id="hourlyRate"
                   name="hourlyRate"
                   step="0.01"
                   min="0"
                   required>

            <label for="maintenanceThreshold">
                Maintenance Threshold:
            </label>

            <input type="number"
                   id="maintenanceThreshold"
                   name="maintenanceThreshold"
                   step="0.01"
                   min="0"
                   required>

            <button type="submit">
                Register Equipment
            </button>

        </form>

        <br>

        <p>
            <a href="equipment">
                View Equipment
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