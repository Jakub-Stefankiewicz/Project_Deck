<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 24.06.2023
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dependencies</title>
</head>
<body>
<table>
    <tr>
        <td>Nazwa eventu</td>
        <td>Zależności</td>
    </tr>

        <c:forEach items="${events}" var="event">
    <tr>
            <td>${event.eventName}</td>
            <td><a href="/schema/dependencies/add/${event.id}">Dodaj zależności do eventu</a> </td>
    </tr>
        </c:forEach>
</table>
</body>
</html>