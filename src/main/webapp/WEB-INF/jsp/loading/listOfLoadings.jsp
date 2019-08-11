<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 10.08.2019
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ListOfLoadings</title>
</head>
<body>
<c:forEach var="loading" items="${listOfAllLoadings}">
    <div>${loading.customer} ${loading.price} ${loading.currency} ${loading.countryOfLoad} ${loading.loadingPlaceCode}</div>
</c:forEach>
</body>
</html>
