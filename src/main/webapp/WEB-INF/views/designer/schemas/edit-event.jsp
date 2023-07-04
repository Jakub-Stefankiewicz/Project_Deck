<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 01.07.2023
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit event</title>
</head>
<body>
Edytujesz event ${eventToEdit.eventName}: <br>
<form:form modelAttribute="eventToEdit" method="post">
    Nazwa: <form:input path="eventName"/>
    Data ukończenia: <form:input type="date" path="expiration"/>
    Czy event jest ostateczny: <form:checkbox path="finalEvent"/>
    <form:hidden path="id"/>
    <form:hidden path="events"/>
    <form:hidden path="endangered"/>
    <form:hidden path="completed"/>
    <form:hidden path="finalEvent"/>
    <form:hidden path="offer"/>
    <input type="submit" value="Zatwierdź zmiany">
</form:form>
</body>
</html>
