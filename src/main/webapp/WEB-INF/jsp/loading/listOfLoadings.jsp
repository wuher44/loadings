<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 10.08.2019
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ListOfLoadings</title>
</head>
<style type="text/css">
    body {
        background: url(https://i.pinimg.com/originals/49/52/67/4952670d3e78c749096d6d1f2536b324.jpg) no-repeat center center fixed;
        background-size: 100vw;
        align-items: center;
    }

    .tg a {
        display: block;
        /*height: 100%;
        width: 100%;*/
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
<INPUT Type="BUTTON" Value="Add loading" Onclick="window.location.href='/addLoading'">
<INPUT Type="BUTTON" Value="Start" Onclick="window.location.href='/start'">
<br>
<br>
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

    <c:forEach var="loading" items="${listOfAllLoadings}">

        <tr class="row">
            <td class="tg-8a48"><a href="#">${loading.truckSetId}</a></td>
            <td class="tg-8a48"><a href="#">${loading.customer}</a></td>
            <td class="tg-8a48"><a href="#">${loading.adr}</a></td>
            <td class="tg-8a48"><a href="#">${loading.price}</a></td>
            <td class="tg-8a48"><a href="#">${loading.currency}</a></td>
            <td class="tg-8a48"><a href="#">${loading.plannedWeight}</a></td>
            <td class="tg-8a48"><a href="#">${loading.countryOfLoad}</a></td>
            <td class="tg-8a48"><a href="#">${loading.loadingPlaceCode}</a></td>
            <td class="tg-8a48">
                <a href="#">${loading.plannedDateAndTimeOfLoad.toLocalDate().toString().replaceAll("-", ".")} ${loading.plannedDateAndTimeOfLoad.toLocalTime()}</a>
            </td>
            <td class="tg-8a48">
                <c:if test="${loading.startOfLoad==null}">
                    <INPUT Type="BUTTON" Value="SET" Onclick="window.location.href='/loading/setLoad/${loading.id}'">
                </c:if>
                <c:if test="${loading.startOfLoad!=null}">
                    <a href="#">${loading.startOfLoad.toLocalDate().toString().replaceAll("-", ".")} ${loading.startOfLoad.toLocalTime()}</a>
                </c:if>
            </td>
            <td class="tg-8a48">
                <c:if test="${loading.endOfLoad==null}">
                    <INPUT Type="BUTTON" Value="SET" Onclick="window.location.href='/loading/setLoad/${loading.id}'">
                </c:if>
                <c:if test="${loading.endOfLoad!=null}">
                    <a href="#">${loading.endOfLoad.toLocalDate().toString().replaceAll("-", ".")} ${loading.endOfLoad.toLocalTime()}</a>
                </c:if>
            </td>
            <td class="tg-8a48">
                <c:if test="${loading.loadedWeight==null}">
                    <INPUT Type="BUTTON" Value="SET" Onclick="window.location.href='/loading/setLoad/${loading.id}'">
                </c:if>
                <c:if test="${loading.loadedWeight!=null}">
                    <a href="#">${loading.loadedWeight}</a>
                </c:if>
            </td>
            <td class="tg-8a48"><a href="#">${loading.countryOfUnload}</a></td>
            <td class="tg-8a48"><a href="#">${loading.unloadingPlaceCode}</a></td>
            <td class="tg-8a48">
                <a href="#">${loading.plannedDateAndTimeOfUnload.toLocalDate().toString().replaceAll("-", ".")} ${loading.plannedDateAndTimeOfUnload.toLocalTime()}</a>
            </td>

            <td class="tg-8a48">


                <c:if test="${loading.startOfUnload==null}">
                    <c:if test="${loading.status=='LOADED'}">
                        <INPUT Type="BUTTON" Value="SET" Onclick="window.location.href='/loading/setUnload/${loading.id}'">
                    </c:if>
                    <c:if test="${loading.status!='LOADED'}">
                        <a href="#">${loading.startOfUnload.toLocalDate().toString().replaceAll("-", ".")} ${loading.startOfUnload.toLocalTime()}</a>
                    </c:if>
                </c:if>
                <c:if test="${loading.startOfUnload!=null}">
                    <a href="#">${loading.startOfUnload.toLocalDate().toString().replaceAll("-", ".")} ${loading.startOfUnload.toLocalTime()}</a>
                </c:if>

            </td>
            <td class="tg-8a48">
                <c:if test="${loading.endOfUnload==null}">
                    <c:if test="${loading.status=='LOADED'}">
                        <INPUT Type="BUTTON" Value="SET" Onclick="window.location.href='/loading/setUnload/${loading.id}'">
                    </c:if>
                    <c:if test="${loading.status!='LOADED'}">
                        <a href="#">${loading.endOfUnload.toLocalDate().toString().replaceAll("-", ".")} ${loading.endOfUnload.toLocalTime()}</a>
                    </c:if>
                </c:if>
                <c:if test="${loading.endOfUnload!=null}">
                    <a href="#">${loading.endOfUnload.toLocalDate().toString().replaceAll("-", ".")} ${loading.endOfUnload.toLocalTime()}</a>
                </c:if>
            </td>
            <td class="tg-8a48"><a href="#">${loading.notes}</a></td>
            <td class="tg-8a48"><a href="#">${loading.status}</a></td>
        </tr>

    </c:forEach>

</table>


</body>
</html>
