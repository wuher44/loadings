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

    .container {
        width: 1000px;
        margin-left: auto;
        margin-right: auto;
    }

    .logo {
        text-align: center;
        padding: 15px;
        background-color: rgba(5, 17, 43, 0.7);
        color: red;
        margin-top: 40px;
    }

    .menu {
        width: 200px;
        /*background-color: rgba(5, 17, 43, 0.7);*/
        float: left;
        height: auto;
        margin-top: 5px;
        margin-bottom: 5px;
        text-align: center;
    }

    .list {
        width: 770px;
        background-color: rgba(5, 17, 43, 0.7);
        margin-top: 5px;
        margin-left: 10px;
        margin-bottom: 5px;
        padding: 10px;
        float: left;
        height: 263px;
        overflow-y: auto;
        color: white;
        text-align: center;

    }

    .footer {
        clear: both;
        text-align: center;
        padding: 15px;
        margin-top: 10px;
        background-color: rgba(5, 17, 43, 0.7);
        color: red;
    }

    .menu a {
        text-decoration: none;
        color: red;
        display: block;
        /*height: 50px;*/
        width: auto;;
    }

    .menu-item {
        background-color: rgba(5, 17, 43, 0.7);
        font-size: 15px;
        border-style: solid;
        border-width: 15px;
        border-color: transparent;
    }

    .menu-item:hover {
        background-color: rgba(5, 17, 43, 0.9);
    }

    .button {
        background-color: red;
        border-radius: 5px;
        border-color: darkred;
        color: white;
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
        color: red;
        font-size: 12px;
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
<div class="container">
    <div class="logo">
        <h1>Your Loadings Manager</h1>
    </div>
    <div class="menu">
        <div class="menu-item"><a href="/addTruckSet">Add TruckSet</a></div>

        <div class="menu-item"><a href="/addDriver">Add Driver</a></div>

        <div class="menu-item"><a href="/addLoading">Add Loading</a></div>

        <div class="menu-item"><a href="/listOfLoadings">List of loadings</a></div>

        <div class="menu-item"><a href="/listOfDrivers">List of drivers</a></div>

        <div class="menu-item"><a href="/truckSetList">List of truckSets</a></div>

    </div>
    <div class="list">
        <fieldset>
            <legend>Search</legend>
            <form method="post" action="/listOfDrivers">
                <input type="text" name="phrase" value="${lastPhrase}">
                <input class="button" type="submit" value="Search">
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
        </fieldset>
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
                    <th class="tg-8a48">
                        <INPUT class="button" Type="BUTTON" Value="EDIT" Onclick="window.location.href='driver/edit/${driver.id}'">
                    </th>
                    <th class="tg-8a48">
                        <INPUT class="button" Type="BUTTON" Value="DELETE" Onclick="window.location.href='driver/delete/${driver.id}'">
                    </th>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="footer">
        Your Loadings Manager &copy wuher44@gmail.com
    </div>
</div>
</body>
</html>
