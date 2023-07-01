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
                <c:forEach var="event" items="${offer.events}">
                    ${event.eventName}<br>
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
Lista dostępnych zależności
<table>
    <tr>
        <td>Nazwa zależności</td>
        <td>Zależności konieczne do spełnienia</td>
        <td>Akcja</td>
    </tr>
    <c:forEach items="${events}" var="event">
        <tr>
            <td>${event.eventName}</td>
            <td><c:forEach var="eventOnList" items="${event.events}">
                ${eventOnList.eventName}<br>
            </c:forEach></td>
            <td><a href="/schema/event/edit/${event.id}">Edytuj</a>,<br>
                <a href="/schema/dependencies/add/${event.id}">Przypisz zależności do spełnienia</a>,<br>
                <c:if test="${not empty event.events}">Aby usunąć event, najpierw usuń zależności</c:if>
                <c:if test="${empty event.events}"><a href="/schema/event/delete/${event.id}"
                                                      onclick="return confirm('Are you sure?')">Usuń</a></c:if> </td>
        </tr>
    </c:forEach>
</table>
<c:if test="${empty events}">Lista zależności jest pusta- dodaj nowe zależności.</c:if><br>
<form:form method="post" modelAttribute="event">
    Nazwa nowego eventu: <form:input path="eventName"/><br>
    Spodziewana data ukończenia eventu: <form:input type="date" path="expiration"/><br>
    Czy to event ostateczny: <form:checkbox path="finalEvent"/>
    <input type="submit"/>
</form:form><br>
<a href="/designer/home">Wróć na stronę główną</a>
</body>
</html>
