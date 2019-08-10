<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 03.08.2019
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Driver</title>
</head>
<style>

</style>
<body>
<h1>Create Driver</h1>

<div>
    <form method="post" action="/addDriver">
        <label>First Name:</label>
        <input type="text" name="firstName" >
        <label>Last Name:</label>
        <input type="text" name="lastName" >
        <input type="submit" value="Add Driver">
    </form>
</div>
<c:if test="${DriverCreated}">
    <p style="color: greenyellow">Driver Created!</p>
</c:if>
<p style="color: red">${info}</p>
<c:forEach var="driver" items="${driversList}">
    <div>${driver.firstName} ${driver.lastName} ${driver.status}</div>
</c:forEach>

<INPUT Type="BUTTON" Value="Exit" Onclick="window.location.href='/start'">


</body>
</html>
