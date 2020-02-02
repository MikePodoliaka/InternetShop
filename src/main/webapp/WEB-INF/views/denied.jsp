<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Access enied</title>
    <style>
        <%@include file='/style/style.css'%>
    </style>
</head>
<body>
<h1>Sorry, requested page is denied for you!</h1>
<form action="${pageContext.request.contextPath}/menu">
    <button type="submit">MENU</button>
</form>
</body>
</html>