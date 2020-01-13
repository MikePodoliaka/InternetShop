<jsp:useBean id="bucket" scope="request" type="internetShop.model.Bucket"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Bucket</title>
</head>
<body>
<br>
<a href="${pageContext.request.contextPath}/getAllItems">Go back to items</a>
</br>
Bucket:
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="item" items="${bucket.items}">
        <tr>
            <td>
                <c:out value="${item.itemId}"/>
            </td>
            <td>
                <c:out value="${item.name}"/>
            </td>
            <td>
                <c:out value="${item.price}"/>
            </td>
            <td>
                <a href="/internetShopPM_war_exploded/deleteItem?item_id=${item.itemId}">DELETE</a>
            </td>

        </tr>
    </c:forEach>
</table>
<br>
<a href="${pageContext.request.contextPath}/order">Complete Order</a>
</br>
<br>
<a href="${pageContext.request.contextPath}/index">Back to main</a>
</br>
</body>
</html>
