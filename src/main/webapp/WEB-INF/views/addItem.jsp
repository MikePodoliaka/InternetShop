
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add Item</title>
</head>
<body>

<form action="/internetShopPM_war_exploded/addItem" method="post">
    <div class="container">
        <h1>add Item</h1>
        <p>Please fill in this form to create an item.</p>
        <hr>

        <label for="item_name"><b>Name</b></label>
        <input type="text" placeholder="Enter item name" name="item_name" required>

        <label for="item_price"><b>Price</b></label>
        <input type="text" placeholder="Enter item price" name="item_price" required>
        <hr>

               <button type="submit" class="addItembtn">Register</button>
    </div>

    <div class="container signin">

    </div>
</form>
</body>
</html>
