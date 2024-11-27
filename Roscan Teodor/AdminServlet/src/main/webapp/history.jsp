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

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Funnel+Sans:ital,wght@0,300..800;1,300..800&family=Parkinsans:wght@300..800&display=swap" rel="stylesheet">
</head>
<body>

    <header>
        <div class="navbar-top">
            <a href="index.jsp" class="logo"><span>Job Hunter</span> | Configurator</a>
            <button class="menu-button" onclick="toggleMenu()">☰</button>
        </div>
        <nav id="nav">
            <a href="admin-servlet?action=results">Results</a>
            <a href="admin-servlet?action=history">History</a>
            <a href="admin-servlet?action=statistics">Statistics</a>
            <a href="admin-servlet?action=config">Configuration</a>
            <a href="admin-servlet?action=credentials">Credentials</a>
        </nav>
    </header>

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
            <th>UUID</th>
            <th>Paths</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (HistoryRecord data : historyList) {
        %>
        <tr>
            <td><%= data.getId() %></td>
            <td><%= data.getUrlString() %></td>
            <td><%= data.getUuid() %></td>
            <td><%= data.getPath() %></td>
            <td><%= data.getStatus() %></td>
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
</body>
</html>