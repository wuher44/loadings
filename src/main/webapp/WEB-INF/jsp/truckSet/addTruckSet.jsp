<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 06.08.2019
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add TruckSet</title>
</head>
<body>
<h1>Create TruckSet</h1>

<div>
    <form method="post" action="/addTruckSet">
        <label>Truck Plate: </label>
        <input type="text" name="truckPlate">
        <label>Trailer Plate: </label>
        <input type="text" name="trailerPlate">
        <br>
        <label>Choose available driver: </label>
        <select id="sel" style="width: 200px" name="driverId">
            <c:forEach var="temp" items="${driversList}">
                <c:if test="${temp.status=='AVAILABLE'}">
                    <option value="${temp.id}">${temp.firstName} ${temp.lastName}</option>
                </c:if>
            </c:forEach>
        </select>
        <br>
        <input type="submit" value="Add TruckSet">
        <input Type="BUTTON" Value="Exit" Onclick="window.location.href='/start'">

    </form>
</div>
<c:if test="${TruckSetCreated}">
    TruckSet Created!
</c:if>
<c:forEach var="driver" items="${driversList}">
    <div>${driver.firstName} ${driver.lastName} ${driver.status}</div>
</c:forEach>


</body>
</html>
