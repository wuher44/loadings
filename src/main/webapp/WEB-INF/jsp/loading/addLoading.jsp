<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 10.08.2019
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Loading</title>
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
        <form method="post" action="/addLoading">
            <label>Choose TruckSet:</label>
            
                <select id="sel-1" style="width: 300px" name="truckSetId">
                    <c:forEach var="temp" items="${truckSetList}">
                        <c:if test="${temp.status=='ACTIVE'}">
                            <option value="${temp.id}">${temp.name}</option>
                        </c:if>
                    </c:forEach>
                </select>

            <label>Customer </label>
            <select id="sel-2" style="width: 200px" name="customerName">
                <c:forEach var="customer" items="${customersList}">
                    <option value="${customer.name}">${customer.name}</option>
                </c:forEach>
            </select>
            <INPUT Type="BUTTON" Value="Add new customer" Onclick="window.location.href='/addCustomer'"><br><br>
            <label>ADR Class:</label>
            <select id="sel-3" style="width: 50px" name="adr">
                <c:forEach var="adrCode" items="${adrCodes}">
                    <option value="${adrCode}">${adrCode}</option>
                </c:forEach>
            </select>
            <label>Weight</label>
            <input type="text" name="weight" value="24.0" style="width: 50px">
            <label>Price: </label>
            <input type="text" name="price">
            <select type="text" name="currency">
                <option>EUR</option>
                <option>PLN</option>
            </select>
            <br><br>
            <label>Country of load :</label>
            <select id="sel-4" style="width: 50px" name="countryOfLoad">
                <c:forEach var="country" items="${countries}">
                    <option value="${country}">${country}</option>
                </c:forEach>
            </select>
            <label>Loading place code:</label>
            <input type="text" name="loadingPlaceCode"><br><br>
            <label>Planned date of load</label>
            <input type="datetime-local" name="plannedDateTimeLoad" value="${dateTimeNow}"> <label>Planned
            date of unload</label>
            <input type="datetime-local" name="plannedDateTimeUnload" value="${dateTimeNow}"><br><br>
            <label>Country of unload :</label>
            <select id="sel-5" style="width: 50px" name="countryOfUnload">
                <c:forEach var="country" items="${countries}">
                    <option value="${country}">${country}</option>
                </c:forEach>
            </select>
            <label>Unloading place code:</label>
            <input type="text" name="unloadingPlaceCode"><br>
            <p>${info}</p><label>Notes:</label>
            <input type="text" name="notes">
            <input type="submit" value="Add loading" style="border-radius: 5px; background-color: red; border-color: darkred;
        color: white;">
        </form>
    </div>
    <div class="footer">
        Your Loadings Manager 1.0 &copy wuher44@gmail.com
    </div>
</div>
</body>
</html>
