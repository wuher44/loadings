<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 03.08.2019
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of drivers</title>
</head>
<style type="text/css">
    body {
        background: url(https://i.pinimg.com/originals/49/52/67/4952670d3e78c749096d6d1f2536b324.jpg) no-repeat center center fixed;
        background-size: 100vw;
        align-items: center;

    }

    .tg {
        border-collapse: collapse;
        border-spacing: 0;
        border-color: #aabcfe;
    }

    .tg td {
        font-family: Arial, sans-serif;
        font-size: 14px;
        padding: 10px 20px;
        border-style: solid;
        border-width: 0px;
        overflow: hidden;
        word-break: normal;
        border-top-width: 1px;
        border-bottom-width: 1px;
        border-color: #aabcfe;
        color: white;
        background-color: rgba(5, 17, 43, 0.6);
        width: 100px;
    }

    .tg th {
        font-family: Arial, sans-serif;
        font-size: 14px;
        font-weight: normal;
        padding: 10px 20px;
        border-style: solid;
        border-width: 0px;
        overflow: hidden;
        word-break: normal;
        border-top-width: 1px;
        border-bottom-width: 1px;
        border-color: #aabcfe;
        color: white;
        background-color: rgba(5, 17, 43, 0.6);
        width: 100px;
    }

    .tg .tg-wp8o {
        border-color: #000000;
        text-align: center;
        vertical-align: top
    }

    .tg .tg-8a48 {
        background-color: rgba(5, 17, 43, 0.6);
        border-color: #000000;
        text-align: center;
        vertical-align: top
    }
</style>
<body>

<form method="post" action="/listOfDrivers">
    <input type="text" name="phrase" value="${lastPhrase}">
    <input type="submit" value="Search">
    <br>
    <label>ALL:</label>
    <input type="radio" name="show" value="all" ${show == 1 ? 'checked' : ''}>
    <label>AVAILABLE:</label>
    <input type="radio" name="show" value="available" ${show == 2 ? 'checked' : ''}>
    <label>NOT_AVAILABLE:</label>
    <input type="radio" name="show" value="not_available" ${show == 3 ? 'checked' : ''}>
    <label>FIRED:</label>
    <input type="radio" name="show" value="fired" ${show == 4 ? 'checked' : ''}>
</form>

<form>
    <select id="sel" style="width: 200px">
        <c:forEach var="temp" items="${driversList}">
            <option value="${temp.id}">${temp.firstName} ${temp.lastName}</option>

        </c:forEach>
    </select>
</form>
<table class="tg" align="center">
    <tr>
        <td class="tg-wp8o">First name</td>
        <td class="tg-wp8o">Last name</td>

        <td class="tg-wp8o">Status</td>
        <td class="tg-wp8o"></td>
        <td class="tg-wp8o"></td>
    </tr>

    <c:forEach var="driver" items="${driversList}">

        <tr>
            <th class="tg-8a48">${driver.firstName}</th>
            <th class="tg-8a48">${driver.lastName}</th>
            <th class="tg-8a48">${driver.status == 'NOT_AVAILABLE' ? driver.currentTruckSet : driver.status}</th>
            <th class="tg-8a48"><a href="driver/edit/${driver.id}" style="color: white">Edit</a></th>
            <th class="tg-8a48"><a href="driver/delete/${driver.id}" style="color: white">Delete</a></th>
        </tr>

    </c:forEach>
</table>

<INPUT Type="BUTTON" Value="Exit" Onclick="window.location.href='/start'">

</body>
</html>
