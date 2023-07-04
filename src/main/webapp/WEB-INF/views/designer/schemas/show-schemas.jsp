<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 01.07.2023
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Show schemas</title>
</head>
<body>
Schematy ofert:

<table>
    <tr>
        <td>Nazwa schematu</td>
        <td>Przypisane zależności</td>
        <td>Akcja</td>
    </tr>
    <c:forEach items="${offers}" var="offer">
        <tr>
            <td>${offer.projectType}</td>
            <td>
                <c:forEach var="event" items="${events}">
                    <c:if test="${event.offer.id==offer.id}">
                    ${event.eventName}<br>
                    </c:if>
                </c:forEach>
            </td>
            <td>
                <a href="/schema/offer/add_events/${offer.id}">Przypisz/usuń zależność</a>,
            </td>
        </tr>
    </c:forEach>
</table>
<c:if test="${empty offers}">Lista ofert jest pusta- dodaj nową ofertę.</c:if><br>
<br><br>
<a href="/designer/home">Wróć na stronę główną</a>
</body>
</html>
