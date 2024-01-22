<!DOCTYPE html>
<html>

<head>
    <title>Book</title>
    <link rel="stylesheet" type="text/css" href="css/book/book.css">
</head>

<body>
    <h1>Book</h1>
    <div class="book-details">
        <p><strong>Id:</strong> ${book.id}</p>
        <p><strong>Title:</strong> ${book.title}</p>
        <p><strong>Author:</strong> ${book.author}</p>
        <p><strong>Price:</strong> ${book.price}</p>
        <p><strong>Year of Publishing:</strong> ${book.yearOfPublishing}</p>
    </div>

    <a href="bookstore?command=books" class="back-button">Back</a>
</body>

</html>