<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add TruckSet</title>
</head>
<style>
    body {
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
        <fieldset>
            <legend>Create TruckSet</legend>
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
                    <input class="button" type="submit" value="Add TruckSet">
                </form>
            </div>
        </fieldset>
        <c:if test="${TruckSetCreated}">
            TruckSet Created!
        </c:if>
    </div>
    <div class="footer">
        Your Loadings Manager 1.0 &copy wuher44@gmail.com
    </div>


    <c:forEach var="driver" items="${driversList}">
    <div>${driver.firstName} ${driver.lastName} ${driver.status}</div>
    </c:forEach>


</body>
</html>
