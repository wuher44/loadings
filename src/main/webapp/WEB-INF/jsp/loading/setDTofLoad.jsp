<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 13.08.2019
  Time: 01:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Set DateTime of Load</title>
</head>
<body>
<fieldset>
    <legend>Set date and time of load</legend>
    <form method="post" action="/loading/setLoad/${loading.id}">
        <label>Start of
            load: </label><input type="datetime-local" name="startOfLoad" value="${loading.startOfLoad}"><br>
        <label>End of
            load: </label><input type="datetime-local" name="endOfLoad" value="${loading.endOfLoad}"><br>
        <input type="submit" value="Set">

    </form>
</fieldset>
</body>
</html>
