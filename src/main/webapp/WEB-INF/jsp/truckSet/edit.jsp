<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 16.08.2019
  Time: 00:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit TruckSet</title>
</head>
<body>

<c:if test="${truckSet.status=='ACTIVE'}">
    <p>This change is irreversible!!!! Are You sure You want to change it????</p>
    <form method="post" action="/truckSet/edit/${truckSet.id}">
        <label>Status:</label><br>
        <label>ACTIVE</label>

        <input type="radio" name="status" value="Active" ${truckSet.status==("ACTIVE") ? 'checked' : ''}>
        <label>NOT_ACTIVE</label>
        <input type="radio" name="status" value="Not_Active" ${truckSet.status==("NOT_ACTIVE") ? 'checked' : ''}>
        <input type="submit" value="Edit">


    </form>
</c:if>
<input type="button" value="Cancel" onclick="history.back()">
</body>
</html>
