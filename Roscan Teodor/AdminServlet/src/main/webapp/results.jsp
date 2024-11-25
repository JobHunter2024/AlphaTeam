<%@ page import="com.example.adminservlet.core.provider.ResultRecord" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JobHunter - Results</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/json5/2.2.1/index.min.js"></script>
    <style>
        /* Styling for the text area */
        textarea {
            width: 100%;
            height: 200px;
            font-family: "Courier New", Courier, monospace;
            font-size: 14px;
            white-space: pre-wrap;
            overflow: auto;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f4f4f4;
        }
    </style>
</head>
<body>
<h1><%= "Results" %>
</h1>
<br/>
<a href="index.jsp">Menu</a>

<h1>Result History</h1>
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