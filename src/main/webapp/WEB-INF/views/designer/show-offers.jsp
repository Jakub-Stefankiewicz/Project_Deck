<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 30.06.2023
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show offers</title>
</head>
<body>
<table>
    <tr>
        <td>Nazwa oferty</td>
        <td>Cena oferty</td>
        <td>Akcja</td>
    </tr>
    <c:forEach items="${offers}" var="offer">
        <tr>
            <td>${offer.projectType}</td>
            <td>${offer.price}</td>
            <td><a href="/designer/offer/edit/${offer.id}"> Edytuj ofertę</a>  <a href="/designer/offer/delete/${offer.id}">Usuń ofertę</a> </td>
        </tr>
    </c:forEach>
<a href="/designer/offer/add">Dodaj nową ofertę do listy</a>
</table>
</body>
</html>
