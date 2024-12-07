<%@ page import="com.example.adminservlet.core.data.extraction.DataToExtract" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>JobHunter - Config</title>
    <script src="scripts/scripts.js"></script>
    <link href="css/config.css" rel="stylesheet">
    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/titles.css" rel="stylesheet">
    <link href="css/table.css" rel="stylesheet">
    <link href="css/form.css" rel="stylesheet">
    <link href="css/miniButton.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">

    <%@ include file="components/font.jsp" %>
</head>
<body>

    <%@ include file="components/navbar.jsp" %>

    <%
        DataToExtract targetElement = (DataToExtract) request.getAttribute("targetData");
        String uuidString = request.getParameter("uuid");
    %>

    <h2 class="sectionTitle">Scraping Configuration</h2>
    <div class="underline">
        <img src="images/underline.png" alt="IGS Section Underline">
    </div>

    <form action="admin-servlet?action=updateConfig" method="post">
        <input type="hidden" name="uuid" value="<%= uuidString != null ? uuidString : "" %>">

        <label for="url">URL:</label>
        <input type="text" id="url" name="url" placeholder="Enter URL" required
               value="<%= targetElement != null ? targetElement.getUrl() : "" %>">
        <br/><br/>

        <label for="pathContainer">Paths:</label>
        <div id="pathContainer">
            <%
                if (targetElement != null && targetElement.getPath() != null) {
                    for (String div : targetElement.splitPath()) {
            %>
            <script>
                addPathInput("<%= div %>");
            </script>
            <%
                    }
                }
            %>
        </div>
        <button type="button" onclick="addPathInput()">Add Path</button>
        <br/><br/>

        <button type="submit">Update Configuration</button>
    </form>

    <h2 class="sectionTitle">Active Configurations</h2>
    <div class="underline">
        <img src="images/underline.png" alt="IGS Section Underline">
    </div>
        <%
            List<DataToExtract> dataList = (List<DataToExtract>) request.getAttribute("dataList");
            if (dataList != null) {
        %>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>URL</th>
                <th>Paths</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (DataToExtract data : dataList) {
            %>
            <tr>
                <td><%= data.getId() %></td>
                <td><a href="<%= data.getUrl() %>"><%= data.getUrl() %></a></td>
                <td><%= data.getPath() %></td>
                <td>
                    <div class="miniButton">
                        <a href="admin-servlet?action=config&uuid=<%= data.getUuid() %>">Edit</a>
                        <a href="admin-servlet?action=deleteConfig&uuid=<%= data.getUuid() %>">Delete</a>
                    </div>
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

        <p>No configurations available.</p>
        <%
            }
        %>

    <%@ include file="components/footer.jsp" %>

</body>
</html>