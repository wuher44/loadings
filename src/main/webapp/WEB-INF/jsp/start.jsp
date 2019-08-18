<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MY
  Date: 03.08.2019
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Loadings</title>
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
            /*background-color: rgba(5, 17, 43, 0.7);*/
            float: left;
            height: auto;
            margin-top: 5px;
            margin-bottom: 5px;
            text-align: center;
        }

        .list {
            width: 770px;
            background-color: rgba(5, 17, 43, 0.7);
            margin-top: 5px;
            margin-left: 10px;
            margin-bottom: 5px;
            padding: 10px;
            float: left;
            height: 263px;
            overflow-y: auto;
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
            color: white;
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
</head>

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
    <div class="list">
        <table class="tg" align="center">
            <tr>
                <th class="tg-wp8o">TruckSet Id</th>
                <th class="tg-wp8o">Customer</th>
                <th class="tg-wp8o">Price</th>
                <th class="tg-wp8o">Curr.</th>
                <th class="tg-wp8o">Country</th>
                <th class="tg-wp8o">Code</th>
                <th class="tg-wp8o">Load Date</th>
                <th class="tg-wp8o">Country</th>
                <th class="tg-wp8o">Code</th>
                <th class="tg-wp8o">Unload Date</th>
                <th class="tg-wp8o">Notes</th>
                <th class="tg-wp8o">Status</th>
            </tr>
            <c:forEach var="loading" items="${list}">

                <tr class="row">
                    <td class="tg-8a48"><a href="#">${loading.truckSetId}</a></td>
                    <td class="tg-8a48"><a href="#">${loading.customer}</a></td>
                    <td class="tg-8a48"><a href="#">${loading.price}</a></td>
                    <td class="tg-8a48"><a href="#">${loading.currency}</a></td>
                    <td class="tg-8a48"><a href="#">${loading.countryOfLoad}</a></td>
                    <td class="tg-8a48"><a href="#">${loading.loadingPlaceCode}</a></td>
                    <td class="tg-8a48">
                        <a href="#">${loading.plannedDateAndTimeOfLoad.toLocalDate().toString().replaceAll("-", ".")}</a>
                    </td>
                    <td class="tg-8a48"><a href="#">${loading.countryOfUnload}</a></td>
                    <td class="tg-8a48"><a href="#">${loading.unloadingPlaceCode}</a></td>
                    <td class="tg-8a48">
                        <a href="#">${loading.plannedDateAndTimeOfUnload.toLocalDate().toString().replaceAll("-", ".")}</a>
                    </td>
                    <td class="tg-8a48"><a href="#">${loading.notes}</a></td>
                    <td class="tg-8a48"><a href="#">${loading.status}</a></td>
                </tr>

            </c:forEach>

        </table>
    </div>
    <div class="footer">
        Your Loadings Manager &copy wuher44@gmail.com
    </div>
</div>
</body>
</html>
