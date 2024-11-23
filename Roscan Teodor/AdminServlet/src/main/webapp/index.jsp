<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JobHunter - Menu</title>
</head>
<body>
<h1><%= "Main Menu" %>
</h1>
<br/>
<a href="admin-servlet?action=results">Scraping Results</a>
<br>
<a href="admin-servlet?action=history">Scraping History</a>
<br>
<a href="admin-servlet?action=statistics">Scraping Statistics</a>
<br>
<a href="admin-servlet?action=credentials">Manage Admin Credentials</a>
<br>
<a href="admin-servlet?action=config">Manage Scraping Config</a>
</body>
</html>