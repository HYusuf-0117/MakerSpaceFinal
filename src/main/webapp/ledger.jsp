<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="DTO.LedgerEntry" %>
<%@ page import="DTO.User" %>

<%
    User user = (User) request.getAttribute("user");
    List<LedgerEntry> ledgerEntries =
            (List<LedgerEntry>) request.getAttribute("ledgerEntries");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Credit Ledger</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<div class="container">

    <h1>Credit Ledger</h1>

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


    <h2>Account Information</h2>

    <table>
        <tr>
            <th>Name</th>
            <td>
                <%= user.getFirstName() %>
                <%= user.getLastName() %>
            </td>
        </tr>

        <tr>
            <th>Credit Balance</th>
            <td>
                <%= String.format("%.2f", user.getCreditBalance()) %>
                credits
            </td>
        </tr>
    </table>


    <hr>


    <h2>Record Credit Activity</h2>

    <form action="ledger" method="post">

        <table>
            <tr>
                <th>Activity Type</th>
                <td>
                    <select name="activityType" required>
                        <option value="DONATION">Donation</option>
                        <option value="TRAINING">Training</option>
                        <option value="MAINTENANCE">Maintenance</option>
                        <option value="WORK_ORDER">Work Order</option>
                    </select>
                </td>
            </tr>

            <tr>
                <th>Hours</th>
                <td>
                    <input type="number"
                           name="hours"
                           step="0.1"
                           min="0"
                           value="0"
                           required>
                </td>
            </tr>

            <tr>
                <th>Donation Value</th>
                <td>
                    <input type="number"
                           name="donationValue"
                           step="0.01"
                           min="0"
                           value="0"
                           required>
                </td>
            </tr>

            <tr>
                <th>Work Orders Completed</th>
                <td>
                    <input type="number"
                           name="workOrdersCompleted"
                           min="0"
                           value="0"
                           required>
                </td>
            </tr>

            <tr>
                <th>Training Hours</th>
                <td>
                    <input type="number"
                           name="trainingHours"
                           min="0"
                           value="0"
                           required>
                </td>
            </tr>

            <tr>
                <th>Maintenance Hours</th>
                <td>
                    <input type="number"
                           name="maintenanceHours"
                           min="0"
                           value="0"
                           required>
                </td>
            </tr>
        </table>

        <br>

        <button type="submit">
            Record Activity
        </button>

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
                    <td>
                        <%= entry.getLedgerId() %>
                    </td>

                    <td>
                        <%= entry.getEntryType() %>
                    </td>

                    <td>
                        <%= String.format("%.2f", entry.getAmount()) %>
                    </td>

                    <td>
                        <%= entry.getSourceType() %>
                    </td>

                    <td>
                        <%= entry.getDescription() %>
                    </td>

                    <td>
                        <%= entry.getEntryDate() %>
                    </td>
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

</body>
</html>