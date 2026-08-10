<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DTO.LedgerEntry" %>
<%@ page import="DTO.User" %>

<%
    User user = (User) request.getAttribute("user");
    List<LedgerEntry> ledgerEntries =
            (List<LedgerEntry>) request.getAttribute("ledgerEntries");
%>

<div class="container">
    <h1>Credit Ledger</h1>

    <% if (request.getAttribute("message") != null) { %>
        <p class="success"><%= request.getAttribute("message") %></p>
    <% } %>

    <% if (request.getAttribute("error") != null) { %>
        <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>

    <h2>Account Information</h2>

    <p>
        <strong>Name:</strong>
        <%= user.getFirstName() %> <%= user.getLastName() %>
    </p>

    <p>
        <strong>Current Credit Balance:</strong>
        <%= user.getCreditBalance() %>
    </p>

    <hr>

    <h2>Record Credit Activity</h2>

    <form action="ledger" method="post">
        <label>Activity Type:</label>
        <select name="activityType" required>
            <option value="DONATION">Donation</option>
            <option value="TRAINING">Training</option>
            <option value="MAINTENANCE">Maintenance</option>
            <option value="WORK_ORDER">Work Order</option>
        </select>

        <br><br>

        <label>Hours:</label>
        <input type="number" name="hours" step="0.1" min="0" value="0" required>

        <br><br>

        <label>Donation Value:</label>
        <input type="number" name="donationValue" step="0.01" min="0" value="0" required>

        <br><br>

        <label>Work Orders Completed:</label>
        <input type="number" name="workOrdersCompleted" min="0" value="0" required>

        <br><br>

        <label>Training Hours:</label>
        <input type="number" name="trainingHours" min="0" value="0" required>

        <br><br>

        <label>Maintenance Hours:</label>
        <input type="number" name="maintenanceHours" min="0" value="0" required>

        <br><br>

        <button type="submit">Record Activity</button>
    </form>

    <hr>

    <h2>Transaction History</h2>

    <% if (ledgerEntries != null && !ledgerEntries.isEmpty()) { %>
        <table>
            <tr>
                <th>ID</th>
                <th>Type</th>
                <th>Amount</th>
                <th>Source</th>
                <th>Description</th>
                <th>Date</th>
            </tr>

            <% for (LedgerEntry entry : ledgerEntries) { %>
                <tr>
                    <td><%= entry.getLedgerId() %></td>
                    <td><%= entry.getEntryType() %></td>
                    <td><%= entry.getAmount() %></td>
                    <td><%= entry.getSourceType() %></td>
                    <td><%= entry.getDescription() %></td>
                    <td><%= entry.getEntryDate() %></td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>No ledger transactions found.</p>
    <% } %>

    <br>

    <p>
        <a href="dashboard.jsp">Back to Dashboard</a>
    </p>
</div>