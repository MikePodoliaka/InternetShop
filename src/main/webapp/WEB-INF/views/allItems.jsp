<jsp:useBean id="items" scope="request" type="java.util.List<internetShop.model.Item>"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: PM
  Date: 1/9/2020
  Time: 12:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Items</title>
</head>
<body>
Items:
<table border="1">
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Id</th>
    </tr>
    <c:forEach var="item" items="${items}">
    <tr>
        <td>
            <c:out value="${item.name}"/>
        </td>
        <td>
            <c:out value="${item.price}"/>
        </td>
        <td>
            <c:out value="${item.itemId}"/>
        </td>

    </tr>
    </c:forEach>
</table>
</body>
</html>
