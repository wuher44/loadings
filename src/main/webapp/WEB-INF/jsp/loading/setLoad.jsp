<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Set Load</title>
</head>
<style type="text/css">
    body {
        background-size: 100vw;
        align-items: center;
    }

    .tg a {
        display: block;
        text-decoration: none;
        color: red;
        padding: revert;
    }

    .tg {
        border-collapse: collapse;
        border-spacing: 0;
        border-color: #aabcfe;
    }

    .tg tr.row:hover {
        background-color: rgba(5, 17, 43, 0.9);
    }

    .tg td {
        font-family: Arial, sans-serif;
        font-size: 1vw;
        color: red;
        padding: 10px 5px;
        border-style: solid;
        border-width: 0px;
        overflow: hidden;
        word-break: normal;
        border-top-width: 1px;
        border-bottom-width: 1px;
        border-left-width: 1px;
        border-color: #aabcfe;
        background-color: rgba(5, 17, 43, 0.7);
        width: 4.76%;
    }

    .tg th {
        font-family: Arial, sans-serif;
        font-size: 1vw;
        color: black;
        font-weight: normal;
        padding: 10px 5px;
        border-style: solid;
        border-width: 0px;
        overflow: hidden;
        word-break: normal;
        border-top-width: 1px;
        border-bottom-width: 1px;
        border-left-width: 1px;
        border-color: #aabcfe;
        background-color: rgba(5, 17, 43, 0.7);
        width: 4.76%;
        height: 100%;
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
<table class="tg" align="center">
    <tr>
        <th class="tg-wp8o" rowspan="2">TruckSet Id</th>
        <th class="tg-wp8o" rowspan="2">Customer</th>
        <th class="tg-wp8o" rowspan="2">ADR</th>
        <th class="tg-wp8o" rowspan="2">Price</th>
        <th class="tg-wp8o" rowspan="2">Curr.</th>
        <th class="tg-wp8o" rowspan="2">Pl. Weight</th>
        <th class="tg-wp8o" colspan="6">Load</th>
        <th class="tg-wp8o" colspan="5">Unload</th>
        <th class="tg-wp8o" rowspan="2">Notes</th>
        <th class="tg-wp8o" rowspan="2">Status</th>
    </tr>
    <tr>
        <th class="tg-wp8o">Country</th>
        <th class="tg-wp8o">Code</th>
        <th class="tg-wp8o">Pl. Date and Time</th>
        <th class="tg-wp8o">Start</th>
        <th class="tg-wp8o">End</th>
        <th class="tg-wp8o">Loaded Weight</th>
        <th class="tg-wp8o">Country</th>
        <th class="tg-wp8o">Code</th>
        <th class="tg-wp8o">Pl. Date and Time</th>
        <th class="tg-wp8o">Start</th>
        <th class="tg-wp8o">End</th>
    </tr>
    <tr class="row">
        <td class="tg-8a48">${loading.truckSetId}</td>
        <td class="tg-8a48">${loading.customer}</td>
        <td class="tg-8a48">${loading.adr}</td>
        <td class="tg-8a48">${loading.price}</td>
        <td class="tg-8a48">${loading.currency}</td>
        <td class="tg-8a48">${loading.plannedWeight}</td>
        <td class="tg-8a48">${loading.countryOfLoad}</td>
        <td class="tg-8a48">${loading.loadingPlaceCode}</td>
        <td class="tg-8a48">
            ${loading.plannedDateAndTimeOfLoad.toLocalDate().toString().replaceAll("-", ".")} ${loading.plannedDateAndTimeOfLoad.toLocalTime()}
        </td>
        <td class="tg-8a48">

            <c:if test="${loading.startOfLoad!=null}">
                ${loading.startOfLoad.toLocalDate().toString().replaceAll("-", ".")} ${loading.startOfLoad.toLocalTime()}
            </c:if>
        </td>
        <td class="tg-8a48">

            <c:if test="${loading.endOfLoad!=null}">
                ${loading.endOfLoad.toLocalDate().toString().replaceAll("-", ".")} ${loading.endOfLoad.toLocalTime()}
            </c:if>
        </td>
        <td class="tg-8a48">
            <c:if test="${loading.loadedWeight!=null}">
                ${loading.loadedWeight}
            </c:if>
        </td>
        <td class="tg-8a48">${loading.countryOfUnload}</td>
        <td class="tg-8a48">${loading.unloadingPlaceCode}</td>
        <td class="tg-8a48">
            ${loading.plannedDateAndTimeOfUnload.toLocalDate().toString().replaceAll("-", ".")} ${loading.plannedDateAndTimeOfUnload.toLocalTime()}
        </td>
        <td class="tg-8a48">
            <c:if test="${loading.startOfUnload!=null}">
                ${loading.startOfUnload.toLocalDate().toString().replaceAll("-", ".")} ${loading.startOfUnload.toLocalTime()}
            </c:if>
        </td>
        <td class="tg-8a48">

            <c:if test="${loading.endOfUnload!=null}">
                ${loading.endOfUnload.toLocalDate().toString().replaceAll("-", ".")} ${loading.endOfUnload.toLocalTime()}
            </c:if>
        </td>
        <td class="tg-8a48">${loading.notes}</td>
        <td class="tg-8a48">${loading.status}</td>
    </tr>
</table>
<br>
<br>
<div style="text-align: center">
    <fieldset style="background-color: rgba(5, 17, 43, 0.7); width: 400px; color: red; display: inline-block;">
        <legend>Set load</legend>
        <form method="post" action="/loading/setLoad/${loading.id}">
            <label>Start of
                load: </label><input type="datetime-local" name="startOfLoad" value="${loading.startOfLoad}"><br><br>
            <label>End of
                load: </label><input type="datetime-local" name="endOfLoad" value="${loading.endOfLoad}">
            <p style="color: red">${info}</p><br>
            <label>Weight: </label><input type="text" name="weight" style="width: 50px"><br><br>
            <input type="submit" value="Set">
            <INPUT Type="BUTTON" Value="Exit" Onclick="window.location.href='/listOfLoadings'">
        </form>
    </fieldset>
</div>
</body>
</html>
