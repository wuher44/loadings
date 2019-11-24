<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Driver</title>
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
        <form method="post" action="/driver/edit/${driver.id}">
            <fieldset>
                <legend>Edit</legend>
                <label>First Name:</label>
                <input type="text" name="firstName" value="${driver.firstName}">
                <label>Last Name:</label>
                <input type="text" name="lastName" value="${driver.lastName}"><br>
                <label>Status:</label><br>
                <c:if test="${driver.status!='NOT_AVAILABLE'}">
                    <label>AVAILABLE</label>
                    <input type="radio" name="status" value="Available" ${driver.status==("AVAILABLE") ? 'checked' : ''}>
                </c:if>
                <c:if test="${driver.status=='NOT_AVAILABLE'}">
                    <label>NOT_AVAILABLE</label>
                    <input type="radio" name="status" value="Not_Available" ${driver.status==("NOT_AVAILABLE")  ? 'checked' : ''}>
                </c:if>
                <c:if test="${driver.status!='NOT_AVAILABLE'}">
                    <label>FIRED</label>
                    <input type="radio" name="status" value="Fired" ${driver.status==("FIRED")  ? 'checked' : ''}>
                </c:if>
                <br>
                <p style="color: red">${info}</p>
                <input class="button" type="submit" value="Edit">
            </fieldset>
        </form>
    </div>
    <div class="footer">
        Your Loadings Manager 1.0 &copy wuher44@gmail.com
    </div>
</div>
</body>
</html>
