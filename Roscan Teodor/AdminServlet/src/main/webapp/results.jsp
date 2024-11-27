<%@ page import="com.example.adminservlet.core.provider.ResultRecord" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JobHunter - Results</title>
    <script src="scripts/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/json5/2.2.1/index.min.js"></script>
    <link href="css/results.css" rel="stylesheet">
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

    <h2 class="sectionTitle">Scraping Results</h2>
    <div class="underline">
        <img src="images/underline.png" alt="IGS Section Underline">
    </div>

    <%
        List<ResultRecord> resultList = (List<ResultRecord>) request.getAttribute("resultList");
        if (resultList != null) {
    %>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>URL</th>
            <th>Date</th>
            <th>UUID</th>
            <th>Content</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (ResultRecord data : resultList) {
        %>
        <tr>
            <td><%= data.getId() %></td>
            <td><%= data.getUrl() %></td>
            <td><%= data.getDate() %></td>
            <td><%= data.getUUID() %></td>
            <td>
                <textarea readonly><%= data.getContent() %></textarea>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
    } else {
    %>

    <p>No results available.</p>
    <%
        }
    %>
</body>
</html>