<%@ page import="com.example.adminservlet.core.provider.DataToExtract" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.example.adminservlet.core.provider.DataToExtractAdvanced" %>
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

    <%@ include file="../components/font.jsp" %>
</head>
<body>

    <%@ include file="../components/navbar.jsp" %>

    <%
        DataToExtract targetElement = (DataToExtract) request.getAttribute("targetData");
        String uuidString = request.getParameter("uuid");

        DataToExtractAdvanced targetElementAdvanced = (DataToExtractAdvanced) request.getAttribute("targetDataAdvanced");
        String uuidStringAdvanced = request.getParameter("uuidAdvanced");
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





    <form action="admin-servlet?action=updateConfigAdvanced" method="post">
        <input type="hidden" name="uuidAdvanced" value="<%= uuidStringAdvanced != null ? uuidStringAdvanced : "" %>">

        <label for="urlAdvanced">URL:</label>
        <input type="text" id="urlAdvanced" name="urlAdvanced" placeholder="Enter URL" required
               value="<%= targetElementAdvanced != null ? targetElementAdvanced.getUrl() : "" %>">
        <br/><br/>

        <label for="jobUrlPath">Job Url Jsoup Path:</label>
        <input type="text" id="jobUrlPath" name="jobUrlPath" placeholder="Enter Job Url Jsoup Path"
               value="<%= targetElementAdvanced != null ? targetElementAdvanced.getJobUrlPath() : "" %>">
        <br/><br/>

        <label for="jobDescriptionPath">Job Description Jsoup Path:</label>
        <input type="text" id="jobDescriptionPath" name="jobDescriptionPath" placeholder="Enter Job Description Jsoup Path"
               value="<%= targetElementAdvanced != null ? targetElementAdvanced.getJobDescriptionPath() : "" %>">
        <br/><br/>

        <label for="jobLocationPath">Job Location Jsoup Path:</label>
        <input type="text" id="jobLocationPath" name="jobLocationPath" placeholder="Enter Job Location Jsoup Path"
               value="<%= targetElementAdvanced != null ? targetElementAdvanced.getJobLocationPath() : "" %>">
        <br/><br/>

        <label for="jobCompanyPath">Job Company Jsoup Path:</label>
        <input type="text" id="jobCompanyPath" name="jobCompanyPath" placeholder="Enter Job Company Jsoup Path"
               value="<%= targetElementAdvanced != null ? targetElementAdvanced.getJobCompanyPath() : "" %>">
        <br/><br/>

        <label for="jobTitlePath">Job Title Jsoup Path:</label>
        <input type="text" id="jobTitlePath" name="jobTitlePath" placeholder="Enter Job Title Jsoup Path"
               value="<%= targetElementAdvanced != null ? targetElementAdvanced.getJobTitlePath() : "" %>">
        <br/><br/>

        <label for="jobDatePath">Job Date Jsoup Path:</label>
        <input type="text" id="jobDatePath" name="jobDatePath" placeholder="Enter Job Date Jsoup Path"
               value="<%= targetElementAdvanced != null ? targetElementAdvanced.getJobDatePath() : "" %>">
        <br/><br/>

        <label for="followLink">Follow Link?:</label>
        <input type="checkbox" id="followLink" name="followLink"
            <%= targetElementAdvanced != null && targetElementAdvanced.getFollowLink() ? "checked" : "" %>>
        <br/><br/>


        <button type="submit">Update Configuration</button>
    </form>

    <h2 class="sectionTitle">Active Advanced Configurations</h2>
    <div class="underline">
        <img src="images/underline.png" alt="IGS Section Underline">
    </div>
    <%
        List<DataToExtractAdvanced> dataListAdvanced = (List<DataToExtractAdvanced>) request.getAttribute("dataListAdvanced");
        if (dataListAdvanced != null) {
    %>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>JobBoard URL</th>
            <th>Job URL Path</th>
            <th>Description Path</th>
            <th>Location Path</th>
            <th>Company Path</th>
            <th>Title Path</th>
            <th>Date Path</th>
            <th>Follow Link</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (DataToExtractAdvanced data : dataListAdvanced) {
        %>
        <tr>
            <td><%= data.getId() %></td>
            <td><a href="<%= data.getUrl() %>"><%= data.getUrl() %></a></td>
            <td><%= data.getJobUrlPath() %></td>
            <td><%= data.getJobDescriptionPath() %></td>
            <td><%= data.getJobLocationPath() %></td>
            <td><%= data.getJobCompanyPath() %></td>
            <td><%= data.getJobTitlePath() %></td>
            <td><%= data.getJobDatePath() %></td>
            <td>
                <input type="checkbox" disabled
                    <%= data.getFollowLink() ? "checked" : "" %>>
            </td>
            <td>
                <div class="miniButton">
                    <a href="admin-servlet?action=configAdvanced&uuidAdvanced=<%= data.getUuid() %>">Edit</a>
                    <a href="admin-servlet?action=deleteConfigAdvanced&uuidAdvanced=<%= data.getUuid() %>">Delete</a>
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

    <%@ include file="../components/footer.jsp" %>

</body>
</html>