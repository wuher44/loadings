<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 10.08.2019
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of TruckSets</title>
</head>
<body>

<c:forEach var="truckSet" items="${truckSetList}">
    <div>${truckSet.name} ${truckSet.status} <a href="/truckSet/edit/${truckSet.id}">Change Status</a></div>
</c:forEach>
</body>
</html>
