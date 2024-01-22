<!DOCTYPE html>
<html>

<head>
    <title>User</title>
    <link rel="stylesheet" type="text/css" href="css/user/user.css">
</head>

<body>
    <h1>User</h1>
    <div class="user-details">
        <p><strong>Id:</strong> ${user.id}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>Password:</strong> ${user.password}</p>
    </div>

    <a href="bookstore?command=users" class="back-button">Back</a>
</body>

</html>