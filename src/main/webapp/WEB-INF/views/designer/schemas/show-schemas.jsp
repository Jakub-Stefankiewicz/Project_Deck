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
Schematy:

<table>
    <tr>
        <td>Nazwa schematu</td>
        <td>Przypisane zależności</td>
        <td>Akcja</td>
    </tr>
    <c:forEach items="${trees}" var="tree">
        <tr>
            <td>${tree.treeName}</td>
            <td>
                <c:forEach var="event" items="${tree.events}">
                    ${event.eventName}<br>
                </c:forEach>
            </td>
            <td><a href="/schema/tree/edit/${tree.id}">Edytuj</a>,
                <a href="/schema/tree/add_events/${tree.id}">Przypisz/usuń zależność</a>,
                <a href="/schema/tree/delete/${tree.id}">Usuń</a></td>
        </tr>
    </c:forEach>
</table>
<c:if test="${empty trees}">Lista schematów jest pusta- dodaj nowy schemat.</c:if><br>
<form:form modelAttribute="tree" method="post">
    Nazwa nowego drzewa:<form:input path="treeName"/><br>
    <input type="submit" value="Zatwierdź">
</form:form>
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
                <a href="/schema/event/delete/${event.id}">Usuń</a></td>
        </tr>
    </c:forEach>
</table>
<c:if test="${empty events}">Lista zależności jest pusta- dodaj nowe zależności.</c:if><br>
<form:form method="post" modelAttribute="event">
    Nazwa nowego eventu: <form:input path="eventName"/><br>
    Spodziewana data ukończenia eventu: <form:input type="date" path="expiration"/><br>
    Czy to event ostateczny: <form:checkbox path="finalEvent"/>
    <input type="submit"/>
</form:form>
</body>
</html>
