<jsp:useBean id="errorMsg" scope="request" class="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR</title>
    <style>
        <%@include file='/style/style.css'%>
    </style>
</head>
<body>
<h1>ERROR: ${errorMsg}</h1>
</body>
</html>