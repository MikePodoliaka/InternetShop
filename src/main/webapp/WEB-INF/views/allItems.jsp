<jsp:useBean id="items" scope="request" type="java.util.List<internetShop.model.Item>"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <th>AddToBucket</th>
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
            <td>
                <a href="${pageContext.request.contextPath}/addItemToBucket?item_id=${item.itemId}">ADD</a>
            </td>

        </tr>
    </c:forEach>
</table>
<br>
<a href="${pageContext.request.contextPath}/index">Back to main</a>
</br>
</body>
</html>
