<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="orders" scope="request" type="java.util.List<internetShop.model.Order>"/>
<html>
<head>
    <title>User Order</title>
</head>
<body>
<table border="3">
    <tr>
        Orders:
    </tr>
    <tr>
        <td>ID</td>
        <td>Items</td>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.orderId}"/>
            </td>
            <td>
                <c:out value="${order.items}"/>
            </td>
            <td>
                <a href="/internetShopPM_war_exploded/deleteOrder?order_id=${order.orderId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="${pageContext.request.contextPath}/index">Back to main</a>
</br>
</body>
</html>
