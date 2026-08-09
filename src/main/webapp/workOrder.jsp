<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Work Order</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

    <div class="container">

        <h1>Create Work Order</h1>

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

        <form action="workorder" method="post">

            <label for="requestorId">Requestor ID:</label>

            <input type="number"
                   id="requestorId"
                   name="requestorId"
                   min="1"
                   required>

            <label for="description">Description:</label>

            <textarea id="description"
                      name="description"
                      rows="4"
                      cols="40"
                      required></textarea>

            <label for="priority">Priority:</label>

            <select id="priority" name="priority">
                <option value="NORMAL">Normal</option>
                <option value="HIGH">High</option>
                <option value="URGENT">Urgent</option>
            </select>

            <label for="materials">Materials:</label>

            <input type="text"
                   id="materials"
                   name="materials"
                   placeholder="Example: PLA Filament, Wood, Screws">

            <label for="externalClientName">
                External Client Name:
            </label>

            <input type="text"
                   id="externalClientName"
                   name="externalClientName"
                   placeholder="Optional">

            <button type="submit">
                Create Work Order
            </button>

        </form>

        <br>

        <p>
            <a href="dashboard.jsp">
                Back to Dashboard
            </a>
        </p>

    </div>

</body>
</html>