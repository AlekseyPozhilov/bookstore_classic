<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>BookStore</title>
        <link rel="stylesheet" type="text/css" href="css/home/home.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background-color: #f5f5f5;
        }

        .container {
            text-align: center;
        }

        h1 {
            font-size: 36px;
            margin-bottom: 20px;
        }

        img {
            width: 500px;
            height: auto;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to Bookstore, Dear Guest!</h1>
        <img src="images/bookstore.png" alt="bookstore">
    </div>
     <jsp:include page="navbar.jsp" />
</body>
</html>