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
<form:form modelAttribute="offer" method="post">
    <form:checkboxes path="events" items="${events}" itemLabel="eventName"/>
    <form:hidden path="id" />
    <form:hidden path="projectType"/>
    <form:hidden path="price"/>
    <form:hidden path="designer"/>
    <input type="submit"/>
</form:form>
</body>
</html>
