<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 03.08.2019
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Driver</title>
</head>
<style>
    body {
        background: url(https://i.pinimg.com/originals/49/52/67/4952670d3e78c749096d6d1f2536b324.jpg) no-repeat center center fixed;
        background-size: 100vw;
    }

    .edit-container {
        margin-top: 50px;
        text-align: center;
    }

    .edit {
        color: white;
        background-color: rgba(5, 17, 43, 0.6);
        width: 50%;

        display: inline-block;

    }
</style>
<body>
<div class="edit-container">
    <div class="edit">
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
                <p style="color: crimson">Nie zmianiać tutaj statusu!!!!!!!!!!!!!!!!!!</p>
                <br>
                <input type="submit" value="Edit">
                <input type="button" value="Cancel" onclick="history.back()">
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>
