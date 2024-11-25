<%@ page import="com.example.adminservlet.core.provider.HistoryRecord" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JobHunter - History</title>
</head>
<body>
<h1><%= "History" %>
</h1>
<br/>
<a href="index.jsp">Menu</a>

<h1>Scraping History</h1>
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
        <td><%= data.getUUID() %></td>
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