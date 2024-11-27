<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JobHunter - Credentials</title>
    <script src="scripts/scripts.js"></script>
    <link href="css/credentials.css" rel="stylesheet">
    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/titles.css" rel="stylesheet">

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

    <h2 class="sectionTitle">Admin Credentials</h2>
    <div class="underline">
        <img src="images/underline.png" alt="IGS Section Underline">
    </div>
</body>
</html>