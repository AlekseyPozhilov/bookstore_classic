<%@ page contentType = "text/html; charset = UTF-8" language = "java" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/forms/universalForm.css">
<title>Register New Book</title>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<h1>Register New Book</h1>

<form method="post" action="bookstore">
    <input name="command" type="hidden" value="create_book"/>
    <label for="title-input">Title:</label>
    <input id="title-input" name="title" type="text" required/>
    <br/>
    <label for="author-input">Author:</label>
    <input id="author-input" name="author" type="text" required/>
    <br/>
    <label for="isbn-input">ISBN:</label>
    <input id="isbn-input" name="isbn" type="text" required/>
    <br/>
    <label for="price-input">PRICE:</label>
    <input id="price-input" name="price" type="number" required/>
    <br/>
    <label for = "numberOfPages-input">PAGES:</label>
    <input id = "numberOfPages-input" name = "numberOfPages" type = "number" required/>
    <br/>
    <label for = "yearOfPublishing-input">yearOfPublishing:</label>
    <input id = "yearOfPublishing-input" name = "yearOfPublishing" type = "number" required/>
    <br/>
    <input type="submit" value="Create">
</form>
</body>
</html>