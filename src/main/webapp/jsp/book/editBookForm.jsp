<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/forms/universalForm.css">
        <title>Edit book</title>
    </head>
<body>
    <jsp:include page="../navbar.jsp"/>
    <h1>Edit book</h1>
    <form method="post" action="bookstore">
        <input name="command" type="hidden" value="edit_book"/>
        <input name="id" type="hidden" value="${requestScope.book.id}"/>
        <label for="title-input">TITLE: </label>
        <input id="title-input" name="title" type="text" required value="${book.title}" required/>
        <br/>
        <label for="author-input">AUTHOR: </label>
        <input id="author-input" name="author" type="text" required value="${book.author}" required/>
        <br/>
        <label for="price-input">PRICE: </label>
        <input id="price-input" name="price" type="number" required value="${book.price}" required/>
        <br/>
         <label for = "numberOfPages-input">PAGES:</label>
         <input id = "numberOfPages-input" name = "numberOfPages" type = "number" required/>
        <br/>
         <label for = "yearOfPublishing-input">yearOfPublishing:</label>
         <input id = "yearOfPublishing-input" name = "yearOfPublishing" type = "number" required/>
        <br/>
        <input type="submit" value="SAVE">
    </form>
</body>
</html>