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
<style>
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

    .form {
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
    <div class="form">
        <h2>Create TruckSet</h2>
        <div>
            <form method="post" action="/addTruckSet">
                <label>Truck Plate: </label>
                <input type="text" name="truckPlate"><br><br>
                <label>Trailer Plate: </label>
                <input type="text" name="trailerPlate">
                <br><br>
                <label>Choose available driver: </label>
                <select id="sel" style="width: 200px" name="driverId">
                    <c:forEach var="temp" items="${driversList}">
                        <c:if test="${temp.status=='AVAILABLE'}">
                            <option value="${temp.id}">${temp.firstName} ${temp.lastName}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <br><br>
                <input type="submit" value="Add TruckSet">
                <%--<input Type="BUTTON" Value="Exit" Onclick="window.location.href='/start'">--%>

            </form>
        </div>
        <c:if test="${TruckSetCreated}">
            TruckSet Created!
        </c:if>
    </div>
    <div class="footer">
        Your Loadings Manager &copy wuher44@gmail.com
    </div>





    <c:forEach var="driver" items="${driversList}">
    <div>${driver.firstName} ${driver.lastName} ${driver.status}</div>
    </c:forEach>


</body>
</html>
