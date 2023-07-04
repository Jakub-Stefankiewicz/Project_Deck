<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 01.07.2023
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add events to tree</title>
</head>
<body>
Dodajesz zależności do: <br>
<c:out value="${offer.projectType}"/><br>

<table>
    <tr>
        <td>Nazwa zależności</td>
        <td>Zależności konieczne do spełnienia</td>
        <td>Akcja</td>
    </tr>
    <c:forEach items="${events}" var="eventToEdit">
        <tr>
            <td>${eventToEdit.eventName}</td>
            <td><c:forEach var="eventOnList" items="${eventToEdit.events}">
                ${eventOnList.eventName}<br>
            </c:forEach></td>
            <td><a href="/schema/event/edit/${eventToEdit.id}">Edytuj</a>,<br>
                <a href="/schema/dependencies/add/${eventToEdit.id}">Przypisz zależności do spełnienia</a>,<br>
                <c:if test="${not empty eventToEdit.events}">Aby usunąć event, najpierw usuń zależności</c:if>
                <c:if test="${empty eventToEdit.events}"><a href="/schema/event/delete/${eventToEdit.id}"
                                                      onclick="return confirm('Are you sure?')">Usuń</a></c:if> </td>
        </tr>
    </c:forEach>
</table>
<c:if test="${empty events}">Lista zależności jest pusta- dodaj nowe zależności.</c:if><br>
<form:form method="post" modelAttribute="event">
    Nazwa nowego eventu: <form:input path="eventName"/><br>
    Spodziewana data ukończenia eventu: <form:input type="date" path="expiration"/><br>
    Czy to event ostateczny: <form:checkbox path="finalEvent"/>
    <form:hidden path="offer"/>
    <input type="submit"/>
</form:form><br>
<a href="/schema/list">Wróć do listy schematów/ofert</a>
</body>
</html>
