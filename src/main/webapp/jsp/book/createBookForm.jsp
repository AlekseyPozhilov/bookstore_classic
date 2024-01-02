<%@ page contentType = "text/html; charset = UTF-8" language = "java" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Register New Book</title>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<h1>Register New Book</h1>

<form method="post" action="controller">
    <input name="command" type="hidden" value="create_book"/>
    <label for="title-input">Title:</label>
    <input id="title-input" name="title" type="text"/>
    <br/>
    <label for="author-input">Author:</label>
    <input id="author-input" name="author" type="text"/>
    <br/>
    <label for="isbn-input">ISBN:</label>
    <input id="isbn-input" name="isbn" type="text"/>
    <br/>
    <input type="submit" value="Create">
</form>
</body>
</html>