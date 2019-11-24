<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Driver</title>
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
            <legend>Create Driver</legend>
            <div>
                <form method="post" action="/addDriver">
                    <label>First Name:</label>
                    <input type="text" name="firstName"><br><br>
                    <label>Last Name:</label>
                    <input type="text" name="lastName"><br><br>
                    <input class="button" type="submit" value="Add Driver">
                </form>
            </div>
            <p style="color: red">${info}</p>
            <c:forEach var="driver" items="${driversList}">
                <div>${driver.firstName} ${driver.lastName} ${driver.status}</div>
            </c:forEach>
        </fieldset>
    </div>
    <div class="footer">
        Your Loadings Manager 1.0 &copy wuher44@gmail.com
    </div>
</div>
</body>
</html>
