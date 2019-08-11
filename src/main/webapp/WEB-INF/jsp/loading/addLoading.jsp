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
<body>
<h1>Add Loading:</h1>
<form method="post" action="/addLoading">
    <fieldset>
        <legend>Loading Creation Form</legend>
        <label>Choose TruckSet:</label>
        <form>
            <select id="sel" style="width: 300px" name="truckSetId">
                <c:forEach var="temp" items="${truckSetList}">
                    <c:if test="${temp.status=='ACTIVE'}">
                        <option value="${temp.id}">${temp.name}</option>
                    </c:if>
                </c:forEach>
            </select>
        </form>
        <br>
        <label>Customer </label>
        <input type="text" name="customer"><br>
        <label>ADR:</label>
        <input type="checkbox" name="adr">
        <br>
        <label>Price: </label>
        <input type="text" name="price">
        <select type="text" name="currency">
            <option>EUR</option>
            <option>PLN</option>
        </select>
        <br>
        <label>Country of load :</label>
        <input type="text" name="countryOfLoad" style="width: 50px">
        <label>Loading place code:</label>
        <input type="text" name="loadingPlaceCode"><br>
        <label>Weight</label>
        <input type="text" name="weight" style="width: 50px">
        <br>
        <label>Planned date and time of load</label>
        <input type="datetime-local" name="plannedDateTimeLoad"><br>
        <%--<label>Date and time of load:</label><br>
        <label>Start:</label>
        <input type="datetime-local" name="startOfLoad">
        <label>End:</label>
        <input type="datetime-local" name="endOfLoad">
        <br>--%>
        <label>Country of unload :</label>
        <input type="text" name="countryOfUnload" style="width: 50px">
        <label>Unloading place code:</label>
        <input type="text" name="unloadingPlaceCode"><br>
        <label>Planned date and time of unload</label>
        <input type="datetime-local" name="plannedDateTimeUnload"><br>
        <%--<label>Date and time of unload:</label><br>
        <label>Start:</label>
        <input type="datetime-local" name="startOfUnload">
        <label>End:</label>
        <input type="datetime-local" name="endOfUnload"><br>
        <label>Loaded Weight</label>
        <input type="text" name="weight">--%>
        <label>Notes:</label>
        <input type="text" name="notes"><br>
        <input type="submit" value="Add">
    </fieldset>

</form>
</body>
</html>
