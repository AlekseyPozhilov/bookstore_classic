<%@ page contentType = "text/html; charset = UTF-8" language = "java" %>
<html>
<head>
<link rel = "stylesheet" type = "text/css" href = "css/style.css">
<title>Register new user</title>
</head>
<body>
<jsp:include page = "../navbar.jsp"/>
<h1>Register new user</h1>

<form method = "post" action = "controller">
    <input name = "command" type = "hidden" value = "create_user"/>
    <label for = "email-input">EMAIL:</label>
    <input id = "email-input" name = "email" type = "email"/>
<br/>
    <label for = "password-input">PASSWORD:</label>
    <input id = "password-input" name = "password" type = "password"/>
<br/>
    <input type = "submit" value = "CREATE">
</form>
</body>
</html>