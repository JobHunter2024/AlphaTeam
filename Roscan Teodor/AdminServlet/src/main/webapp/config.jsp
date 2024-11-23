<%@ page import="com.example.adminservlet.core.data.extraction.DataToExtract" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>JobHunter - Config</title>
    <script>
        function addPathInput(key = null, value = null) {
            const pathContainer = document.getElementById("pathContainer");

            const pathDiv = document.createElement("div");
            pathDiv.className = "path-entry";

            const keyInput = document.createElement("input");
            keyInput.type = "text";
            keyInput.name = "pathKey";
            keyInput.placeholder = "Enter Key";
            keyInput.required = true;
            keyInput.value = key ? key : "";

            const valueInput = document.createElement("input");
            valueInput.type = "text";
            valueInput.name = "pathValue";
            valueInput.placeholder = "Enter Value";
            valueInput.required = true;
            valueInput.value = value ? value : "";

            const removeButton = document.createElement("button");
            removeButton.type = "button";
            removeButton.innerText = "Remove";
            removeButton.onclick = function () {
                pathContainer.removeChild(pathDiv);
            };

            pathDiv.appendChild(keyInput);
            pathDiv.appendChild(valueInput);
            pathDiv.appendChild(removeButton);

            pathContainer.appendChild(pathDiv);
        }
    </script>
</head>
<body>
<h1>Scraping Configuration</h1>
<br/>
<a href="index.jsp">Menu</a>
<br/><br/>

    <%
        DataToExtract targetElement = (DataToExtract) request.getAttribute("targetData");
        String uuidString = request.getParameter("uuid");
    %>

    <form action="admin-servlet?action=updateConfig" method="post">
        <input type="hidden" name="uuid" value="<%= uuidString != null ? uuidString : "" %>">

        <label for="url">URL:</label>
        <input type="text" id="url" name="url" placeholder="Enter URL" required
               value="<%= targetElement != null ? targetElement.getUrlString() : "" %>">
        <br/><br/>

        <h3>Paths:</h3>
        <div id="pathContainer">
            <%
                if (targetElement != null && targetElement.getPath() != null) {
                    for (Map.Entry<String, String> entry : targetElement.getPath().entrySet()) {
            %>
            <script>
                addPathInput("<%= entry.getKey() %>", "<%= entry.getValue() %>");
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

    <h1>Configurations</h1>
        <%
            List<DataToExtract> dataList = (List<DataToExtract>) request.getAttribute("dataList");
            if (dataList != null) {
        %>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>URL</th>
                <th>UUID</th>
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
                <td><%= data.getUrlString() %></td>
                <td><%= data.getUUID() %></td>
                <td><%= data.getPath() %></td>
                <td>
                    <a href="admin-servlet?action=deleteConfig&uuid=<%= data.getUUID() %>">Delete</a>
                    <a href="admin-servlet?action=config&uuid=<%= data.getUUID() %>">Edit</a>
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

</body>
</html>