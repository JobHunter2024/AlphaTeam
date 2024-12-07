<%@ page import="com.example.adminservlet.core.provider.HistoryRecord" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JobHunter - History</title>
    <script src="scripts/scripts.js"></script>
    <link href="css/history.css" rel="stylesheet">
    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/titles.css" rel="stylesheet">
    <link href="css/table.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">

    <%@ include file="components/font.jsp" %>
</head>
<body>

    <%@ include file="components/navbar.jsp" %>

    <h2 class="sectionTitle">Scraping History</h2>
    <div class="underline">
        <img src="images/underline.png" alt="IGS Section Underline">
    </div>

    <%
        List<HistoryRecord> historyList = (List<HistoryRecord>) request.getAttribute("historyList");
        if (historyList != null) {
    %>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>URL</th>
            <th>Paths</th>
            <th>Status</th>
            <th>Error</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (HistoryRecord data : historyList) {
        %>
        <tr>
            <td><%= data.getId() %></td>
            <td><a href="<%= data.getUrl() %>"><%= data.getUrl() %></a></td>
            <td><%= data.getPath() %></td>
            <td><%= data.getStatus() %></td>
            <td><%= data.getError() %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
    } else {
    %>

    <p>No history available.</p>
    <%
        }
    %>

    <%@ include file="components/footer.jsp" %>

</body>
</html>