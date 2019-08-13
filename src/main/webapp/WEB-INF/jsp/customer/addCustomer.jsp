<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 12.08.2019
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
<fieldset>
    <legend>Add Customer</legend>
    <form method="post" action="/addCustomer">
        <input type="text" name="customer"><p>${info}</p><br>
        <input type="submit" value="Add Customer"> <INPUT Type="BUTTON" Value="Back" Onclick="window.location.href='/addLoading'">


    </form>
</fieldset>
</body>
</html>
