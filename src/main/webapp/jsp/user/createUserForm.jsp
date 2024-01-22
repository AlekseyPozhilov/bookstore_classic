<%@ page contentType = "text/html; charset = UTF-8" language = "java" %>
<html>
<head>
<title>Register new user</title>
<link rel="stylesheet" type="text/css" href="css/forms/universalForm.css">
</head>
<body>
<jsp:include page = "../navbar.jsp"/>
<h1>Register new user</h1>

<form method = "post" action = "bookstore">
     <input name = "command" type = "hidden" value = "create_user"/>
     <label for = "firstName-input">FIRST NAME:</label>
     <input id = "firstName-input" name = "firstName" type = "text"/>
<br/>
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