<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JobHunter - Credentials</title>
    <script src="scripts/scripts.js"></script>
    <link href="css/credentials.css" rel="stylesheet">
    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/titles.css" rel="stylesheet">
    <link href="css/form.css" rel="stylesheet">
    <link href="css/miniButton.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">

    <%@ include file="../components/font.jsp" %>
</head>
<body>

    <%@ include file="../components/navbar.jsp" %>

    <h2 class="sectionTitle">Admin Credentials</h2>
    <div class="underline">
        <img src="images/underline.png" alt="IGS Section Underline">
    </div>

    <form id="credentialsForm" action="admin-servlet?action=updateCredentials" method="post">
        <label for="newUsername">New Username:</label>
        <input type="text" id="newUsername" name="newUsername" placeholder="Enter New Username" required>
        <br/><br/>

        <label for="newPassword">New Password:</label>
        <input type="password" id="newPassword" name="newPassword" placeholder="Enter New Password" required>
        <br/><br/>

        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm New Password" required>
        <br/><br/>

        <button type="submit" value="update">Update Credentials</button>
    </form>

    <%@ include file="../components/footer.jsp" %>

</body>
</html>