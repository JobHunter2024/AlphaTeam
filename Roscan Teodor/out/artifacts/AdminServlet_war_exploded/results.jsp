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
    <link href="css/footer.css" rel="stylesheet">

    <%@ include file="components/font.jsp" %>
</head>
<body>

    <%@ include file="components/navbar.jsp" %>

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
            <th>Content</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (ResultRecord data : resultList) {
        %>
        <tr>
            <td><%= data.getId() %></td>
            <td><a href="<%= data.getUrl() %>"><%= data.getUrl() %></a></td>
            <td><%= data.getDate() %></td>
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

    <%@ include file="components/footer.jsp" %>

</body>
</html>