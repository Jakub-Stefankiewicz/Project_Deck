<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 27.06.2023
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add dependencies</title>
</head>
<body>
Dodajesz zależności do: <br>
<c:out value="${event.eventName}"/><br>

<form:form modelAttribute="event" method="post">
    <form:checkboxes path="events" items="${eventsList}" itemLabel="eventName"/>
    <form:hidden path="completed" value="${event.completed}" />
    <form:hidden path="endangered" value="${event.endangered}" />
    <form:hidden path="eventName" value="${event.eventName}" />
    <form:hidden path="expiration" value="${event.expiration}" />
    <form:hidden path="tree" value="${event.tree.id}" />
    <input type="submit"/>
</form:form>

</body>
</html>
