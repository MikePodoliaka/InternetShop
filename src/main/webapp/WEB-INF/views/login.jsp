
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>${errorMsg}</div>
<form action="/internetShopPM_war_exploded/login" method="post">
    <div class="container">
        <h1>Login</h1>
        <p>Please fill in this form enter an account.</p>
        <hr>

        <label for="login"><b>Login</b></label>
        <input type="text" placeholder="Enter Login" name="login" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>


        <hr>


        <button type="submit" class="registerbtn">Register</button>
    </div>

    <div class="container signin">
        <p>Already have an account? <a href="#">Sign up</a>.</p>
    </div>
</form>
</body>
</html>
