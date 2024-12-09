<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JobHunter - Login</title>
    <script src="scripts/scripts.js"></script>
    <link href="css/index.css" rel="stylesheet">
    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/titles.css" rel="stylesheet">
    <link href="css/form.css" rel="stylesheet">
    <link href="css/miniButton.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">

    <%@ include file="components/font.jsp" %>
</head>
<body>

    <%@ include file="components/navbar.jsp" %>

    <h2 class="sectionTitle">Job Hunter Login</h2>
    <div class="underline">
        <img src="images/underline.png" alt="IGS Section Underline">
    </div>


    <form id="loginForm" action="j_security_check" method="post">
        <label for="j_username">Username:</label>
        <input type="text" id="j_username" name="j_username" placeholder="Enter Username" required>
        <br/><br/>

        <label for="j_password">Password:</label>
        <input type="password" id="j_password" name="j_password" placeholder="Enter Password" required>
        <br/><br/>

        <button type="submit" value="Login">Login</button>
    </form>


    <%@ include file="components/footer.jsp" %>

</body>
</html>